package com.testres.testrest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.testres.testrest.model.Country;
import com.testres.testrest.service.ICountryService;

import java.util.List;

/**
 * @author konst
 */
@RestController
public class CountryController {
    private final Logger log = LoggerFactory.getLogger(CountryController.class);

    private final ICountryService countryService;

    @Autowired
    public CountryController(ICountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }


}
