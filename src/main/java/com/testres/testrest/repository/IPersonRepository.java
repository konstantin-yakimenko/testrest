package com.testres.testrest.repository;

import com.testres.testrest.exception.NotFoundException;
import com.testres.testrest.model.PersonEntity;
import com.testres.testrest.model.QueryParamsPerson;

import java.util.List;

/**
 * @author konst
 */
public interface IPersonRepository {
    PersonEntity save(PersonEntity person);

    PersonEntity delete(PersonEntity person);

    PersonEntity findById(Long id) throws NotFoundException;

    List<PersonEntity> findByParams(QueryParamsPerson queryParamsPerson);
}
