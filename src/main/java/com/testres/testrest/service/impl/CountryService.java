package com.testres.testrest.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.testres.testrest.model.Country;
import com.testres.testrest.repository.ICountryRepository;
import com.testres.testrest.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author konst
 */
@Service
public class CountryService implements ICountryService {

    private final LoadingCache<Long, Map<Long, Country>> cache;


    @Autowired
    public CountryService(ICountryRepository countryRepository) {

        CacheLoader<Long, Map<Long, Country>> loader = new CacheLoader<Long, Map<Long, Country>>() {
            @Override
            public Map<Long, Country> load(Long key) {
                Map<Long, Country> cache = new ConcurrentHashMap<>();
                List<Country> countries = countryRepository.findAll();
                for (Country country : countries) {
                    cache.put(country.getCountryId(), country);
                }
                return cache;
            }
        };
        cache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(2, TimeUnit.MINUTES)
                .build(loader);
    }

    @Override
    public List<Country> getAllCountries() {
        try {
            return new ArrayList<>(cache.get(1L).values());
        } catch (ExecutionException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Country getCountry(Long id) {
        try {
            return cache.get(1L).get(id);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
