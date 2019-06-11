package com.testres.testrest.repository;

import com.testres.testrest.model.City;

import java.util.List;

/**
 * @author konst
 */
public interface ICityRepository {

    List<City> findAll();

    City findById(Long id);

    List<City> findByTitle(String title);
}
