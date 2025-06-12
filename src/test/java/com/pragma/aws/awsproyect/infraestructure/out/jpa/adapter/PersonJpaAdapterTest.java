package com.pragma.aws.awsproyect.infraestructure.out.jpa.adapter;

import com.pragma.aws.awsproyect.domain.exception.NoDataFoundException;
import com.pragma.aws.awsproyect.domain.model.Person;
import com.pragma.aws.awsproyect.infraestructure.out.jpa.entity.PersonEntity;
import com.pragma.aws.awsproyect.infraestructure.out.jpa.mapper.IPersonEntityMapper;
import com.pragma.aws.awsproyect.infraestructure.out.jpa.repository.IPersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonJpaAdapterTest {
    @Mock
    private IPersonRepository personRepository;

    @Mock
    private IPersonEntityMapper personEntityMapper;

    @InjectMocks
    private PersonJpaAdapter adapter;

    @Test
    void savePerson_shouldMapAndSaveEntity() {
        Person domain = new Person();
        PersonEntity entity = new PersonEntity();
        when(personEntityMapper.toEntity(domain)).thenReturn(entity);

        adapter.savePerson(domain);

        verify(personEntityMapper).toEntity(domain);
        verify(personRepository).save(entity);
    }

    @Test
    void getPersonById_shouldReturnMappedPerson_whenFound() {
        Long id = 1L;
        PersonEntity entity = new PersonEntity();
        Person domain = new Person();

        when(personRepository.findById(id)).thenReturn(Optional.of(entity));
        when(personEntityMapper.toUserModel(entity)).thenReturn(domain);

        Person result = adapter.getPersonById(id);

        assertSame(domain, result);
        verify(personRepository).findById(id);
        verify(personEntityMapper).toUserModel(entity);
    }

    @Test
    void getPersonById_shouldThrowNoDataFoundException_whenNotFound() {
        Long id = 2L;
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> adapter.getPersonById(id));
        verify(personRepository).findById(id);
        verifyNoInteractions(personEntityMapper);
    }

}