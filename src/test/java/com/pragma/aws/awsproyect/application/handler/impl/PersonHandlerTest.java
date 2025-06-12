package com.pragma.aws.awsproyect.application.handler.impl;

import com.pragma.aws.awsproyect.application.dto.request.PersonRequest;
import com.pragma.aws.awsproyect.application.dto.response.PersonResponse;
import com.pragma.aws.awsproyect.application.mapper.request.IPersonRequestMapper;
import com.pragma.aws.awsproyect.application.mapper.response.IPersonResponseMapper;
import com.pragma.aws.awsproyect.domain.api.IPersonServicePort;
import com.pragma.aws.awsproyect.domain.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonHandlerTest {

    @Mock
    private IPersonServicePort personServicePort;

    @Mock
    private IPersonResponseMapper personResponseMapper;

    @Mock
    private IPersonRequestMapper personRequestMapper;

    @InjectMocks
    private PersonHandler handler;

    @Test
    void savePerson_shouldMapAndDelegateToService() {
        PersonRequest request = new PersonRequest();
        Person domainPerson = new Person();
        when(personRequestMapper.toPerson(request)).thenReturn(domainPerson);

        handler.savePerson(request);

        verify(personRequestMapper).toPerson(request);
        verify(personServicePort).savePerson(domainPerson);
        verifyNoMoreInteractions(personServicePort, personRequestMapper);
    }

    @Test
    void getPersonById_shouldCallServiceAndMapResponse() {
        Long id = 7L;
        Person domainPerson = new Person();
        PersonResponse response = new PersonResponse();
        when(personServicePort.getPersonById(id)).thenReturn(domainPerson);
        when(personResponseMapper.toResponse(domainPerson)).thenReturn(response);

        PersonResponse result = handler.getPersonById(id);

        verify(personServicePort).getPersonById(id);
        verify(personResponseMapper).toResponse(domainPerson);
        assertSame(response, result);
    }

}