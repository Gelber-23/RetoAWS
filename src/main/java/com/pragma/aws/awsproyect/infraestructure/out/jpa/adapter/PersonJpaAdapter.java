package com.pragma.aws.awsproyect.infraestructure.out.jpa.adapter;

import com.pragma.aws.awsproyect.domain.model.Person;
import com.pragma.aws.awsproyect.domain.spi.IPersonPersistencePort;
import com.pragma.aws.awsproyect.infraestructure.out.jpa.mapper.IPersonEntityMapper;
import com.pragma.aws.awsproyect.infraestructure.out.jpa.repository.IPersonRepository;
import com.pragma.aws.awsproyect.domain.exception.NoDataFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonJpaAdapter implements IPersonPersistencePort{
    private final IPersonRepository personRepository;
    private final IPersonEntityMapper personEntityMapper;
    @Override
    public void savePerson(Person person) {
        personRepository.save(personEntityMapper.toEntity(person));
    }

    @Override
    public Person getPersonById(Long id) {
        return personEntityMapper.toUserModel(personRepository.findById(id)
                .orElseThrow(NoDataFoundException::new));
    }
}
