package com.testres.testrest.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.testres.testrest.model.Country;
import com.testres.testrest.repository.ICountryRepository;
import com.testres.testrest.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author konst
 */
@Service
public class CountryService implements ICountryService {

    private final ICountryRepository countryRepository;
    private final LoadingCache<Long, Country> cache;


    @Autowired
    public CountryService(ICountryRepository countryRepository) {
        this.countryRepository = countryRepository;

        CacheLoader<Long, Country> loader = new CacheLoader<Long, Country>() {
            @Override
            public Country load(Long key) {
                return countryRepository.getCountry(key);
            }
        };
        cache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(2, TimeUnit.MINUTES)
                .build(loader);
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountry(Long id) {
        try {
            return cache.get(id);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
