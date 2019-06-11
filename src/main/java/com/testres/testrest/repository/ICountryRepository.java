package com.testres.testrest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.testres.testrest.model.Country;

import java.util.List;

/**
 * @author konst
 */
public interface ICountryRepository {

    Country save(Country country);

    Country delete(Country country);

    List<Country> findAll();

    Country findById(Long id);

    Page<Country> findBySearchTerm(String searchTerm, Pageable pageable);
}
