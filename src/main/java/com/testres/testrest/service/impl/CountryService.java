package com.testres.testrest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.testres.testrest.model.Country;
import com.testres.testrest.repository.ICountryRepository;
import com.testres.testrest.service.ICountryService;

import java.util.List;

/**
 * @author konst
 */
@Service
public class CountryService implements ICountryService {

    private final ICountryRepository countryRepository;

    @Autowired
    public CountryService(ICountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
