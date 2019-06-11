package com.testres.testrest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.testres.testrest.exception.NotFoundException;
import com.testres.testrest.model.Interest;
import com.testres.testrest.model.Person;
import com.testres.testrest.model.PersonEntity;
import com.testres.testrest.repository.IInterestRepository;
import com.testres.testrest.repository.IPersonRepository;
import com.testres.testrest.service.IPersonService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author konst
 */
@Service
public class PersonService implements IPersonService {

    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    private final IPersonRepository personRepository;
    private final IInterestRepository intrestRepository;

    @Autowired
    public PersonService(IPersonRepository personRepository, IInterestRepository intrestRepository) {
        this.personRepository = personRepository;
        this.intrestRepository = intrestRepository;
    }

    @Override
    @Transactional
    public Person save(Person person) {
        log.info("save person: {}", person);
        try {
            PersonEntity personEntity = new PersonEntity(person);
            personRepository.save(personEntity);

            intrestRepository.findByPerson(personEntity.getPersonId())
                    .forEach(intrestRepository::delete);
            person.getInterests()
                    .stream()
                    .map(interest -> new Interest(0L, personEntity.getPersonId(), interest) )
                    .forEach(intrestRepository::save);

            person.setPersonId(personEntity.getPersonId());
            person.setCreateDate(personEntity.getCreateDate());
            log.info("Save person: {}", person);
            return person;
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Person getById(Long id) throws NotFoundException {
        log.info("getById: {}", id);
        try {
            PersonEntity personEntity = personRepository.findById(id);
            List<Interest> interests = intrestRepository.findByPerson(id);
            Person person = personEntity.toPerson();
            person.setInterests(interests
                    .stream()
                    .map(Interest::getTitle)
                    .collect(Collectors.toList()));

            log.info("getById return: {}", person);
            return person;
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public Boolean delete(Long id) throws NotFoundException {
        log.info("delete: {}", id);
        try {
            personRepository.delete(personRepository.findById(id));
            log.info("Delete {} is successful", id);
            return true;
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public Boolean premiumSet(Long id) throws NotFoundException {
        log.info("premiumSet: $id");
        try {
            PersonEntity personEntity = personRepository.findById(id);
            personEntity.setPremiumStart(personEntity.getPremiumStart() == null ? ZonedDateTime.now() : personEntity.getPremiumStart());
            personEntity.setPremiumFinish(null);
            personRepository.save(personEntity);

            log.info("premiumSet person: {}", personEntity);
            return true;
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public Boolean premiumUnset(Long id) throws NotFoundException {
        log.info("premiumUnset: {}", id);
        try {
            PersonEntity personEntity = personRepository.findById(id);
            personEntity.setPremiumFinish(personEntity.getPremiumFinish() == null ? ZonedDateTime.now() : personEntity.getPremiumFinish());
            personRepository.save(personEntity);
            log.info("premiumUnset person: {}", personEntity);
            return true;
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }
}
