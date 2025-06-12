package com.pragma.aws.awsproyect.domain.usecase;

import com.pragma.aws.awsproyect.domain.api.IPersonServicePort;
import com.pragma.aws.awsproyect.domain.exception.UserValidationException;
import com.pragma.aws.awsproyect.domain.model.Person;
import com.pragma.aws.awsproyect.domain.spi.IPersonPersistencePort;
import com.pragma.aws.awsproyect.domain.util.ValidationConstant;
import java.util.ArrayList;
import java.util.List;

public class PersonUseCase implements IPersonServicePort {

    private final IPersonPersistencePort personPersistencePort;

    public PersonUseCase(IPersonPersistencePort personPersistencePort) {
        this.personPersistencePort = personPersistencePort;
    }

    @Override
    public void savePerson(Person person) {
        validPerson(person);
        personPersistencePort.savePerson(person);
    }

    @Override
    public Person getPersonById(Long id) {
        return personPersistencePort.getPersonById(id);
    }

    private void  validPerson (Person person){
        List<String> errors = new ArrayList<>();

        if (person.getName() == null || person.getName().isBlank()) {
            errors.add(ValidationConstant.NAME_REQUIRED);
        }


        String mail = person.getEmail();
        if (mail == null || mail.isBlank() || !mail.matches(ValidationConstant.EMAIL_REGEX)) {
            errors.add(ValidationConstant.EMAIL_REQUIRED);
        }

        if (!errors.isEmpty()) {
            throw new UserValidationException(errors);
        }
    }
}
