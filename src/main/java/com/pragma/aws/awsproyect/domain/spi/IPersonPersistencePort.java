package com.pragma.aws.awsproyect.domain.spi;

import com.pragma.aws.awsproyect.domain.model.Person;

public interface IPersonPersistencePort {

    void savePerson(Person person);

    Person getPersonById(Long id);
}
