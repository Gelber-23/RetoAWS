package com.pragma.aws.awsproyect.domain.api;

import com.pragma.aws.awsproyect.domain.model.Person;

public interface IPersonServicePort {

    void savePerson(Person person);

    Person getPersonById(Long id);
}
