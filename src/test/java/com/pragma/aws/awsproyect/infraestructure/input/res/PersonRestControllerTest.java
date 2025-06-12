package com.pragma.aws.awsproyect.infraestructure.input.res;

import com.pragma.aws.awsproyect.application.dto.request.PersonRequest;
import com.pragma.aws.awsproyect.application.dto.response.PersonResponse;
import com.pragma.aws.awsproyect.application.handler.IPersonHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonRestControllerTest {
    @Mock
    private IPersonHandler personHandler;

    @InjectMocks
    private PersonRestController controller;

    @Test
    void savePerson_shouldReturnCreated_andInvokeHandler() {
        PersonRequest request = new PersonRequest();
        request.setName("Alice");
        request.setEmail("alice@example.com");

        ResponseEntity<Void> response = controller.savePerson(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());
        verify(personHandler, times(1)).savePerson(request);
    }

    @Test
    void getPersonById_shouldReturnOk_andBody() {
        Long id = 5L;
        PersonResponse expected = new PersonResponse();
        expected.setName("Bob");
        expected.setEmail("bob@example.com");

        when(personHandler.getPersonById(id)).thenReturn(expected);

        ResponseEntity<PersonResponse> response = controller.getPersonById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(personHandler, times(1)).getPersonById(id);
    }

}