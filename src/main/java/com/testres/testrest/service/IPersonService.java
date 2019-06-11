package com.testres.testrest.service;

import com.testres.testrest.exception.NotFoundException;
import com.testres.testrest.model.Person;

/**
 * @author konst
 */
public interface IPersonService {

    Person save(Person person);

    Person getById(Long id) throws NotFoundException;

    Boolean delete(Long id) throws NotFoundException;

    Boolean premiumSet(Long id) throws NotFoundException;

    Boolean premiumUnset(Long id) throws NotFoundException;
}
