package com.testres.testrest.controller;

import org.springframework.web.bind.annotation.*;
import com.testres.testrest.exception.NotFoundException;
import com.testres.testrest.model.Person;
import com.testres.testrest.service.IPersonService;

import javax.validation.constraints.NotNull;

/**
 * @author konst
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Person save(@RequestBody Person person) {
        return personService.save(person);
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable @NotNull Long id) throws NotFoundException {
        return personService.getById(id);
    }

    @PostMapping("/delete/{id}")
    public Boolean delete(@PathVariable @NotNull Long id) throws NotFoundException {
        return personService.delete(id);
    }

    @PostMapping("/premium/set/{id}")
    public Boolean premiumSet(@PathVariable @NotNull Long id) throws NotFoundException {
        return personService.premiumSet(id);
    }

    @PostMapping("/premium/unset/{id}")
    public Boolean premiumUnset(@PathVariable @NotNull Long id) throws NotFoundException {
        return personService.premiumUnset(id);
    }
}
/*
person - post
{
    "personId": 0,
    "name": "Lanny",
    "sex": "m",
    "birth": "1994-03-24",
    "countryId": 1,
    "cityId": 1,
    "status": "brother of Thor",
    "email": "tor@gmail.ru",
    "approveEmail": "2019-03-24 18:08:56+0300",
    "phone": "89610559900",
    "approvePhone": "2019-03-24 17:55:56+0300",
    "createDate": null,
    "premiumStart": null,
    "premiumFinish": null,
    "about": "I am owner Asgard!",
    "interests": [
        "sex",
        "box",
        "jazz"
    ]
}
 */
