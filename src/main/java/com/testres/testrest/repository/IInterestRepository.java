package com.testres.testrest.repository;

import com.testres.testrest.exception.NotFoundException;
import com.testres.testrest.model.Interest;

import java.util.List;

/**
 * @author konst
 */
public interface IInterestRepository {

    Interest save(Interest interest);

    Interest delete(Interest interest);

    Interest findById(Long id) throws NotFoundException;

    List<Interest> findByPerson(Long id);

    List<Interest> findByTitle(String title);
}
