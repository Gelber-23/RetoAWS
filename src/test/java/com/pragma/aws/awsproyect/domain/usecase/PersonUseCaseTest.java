package com.pragma.aws.awsproyect.domain.usecase;

import com.pragma.aws.awsproyect.domain.exception.UserValidationException;
import com.pragma.aws.awsproyect.domain.model.Person;
import com.pragma.aws.awsproyect.domain.spi.IPersonPersistencePort;
import com.pragma.aws.awsproyect.domain.util.ValidationConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonUseCaseTest {
    @Mock
    private IPersonPersistencePort persistencePort;

    @InjectMocks
    private PersonUseCase useCase;

    private Person validPerson;

    @BeforeEach
    void setUp() {
        validPerson = new Person();
        validPerson.setName("Alice");
        validPerson.setEmail("alice@example.com");
    }

    @Test
    void savePerson_validPerson_callsSave() {
        useCase.savePerson(validPerson);
        verify(persistencePort).savePerson(validPerson);
    }

    @Test
    void savePerson_nullName_throwsValidationException() {
        validPerson.setName(null);
        UserValidationException ex = assertThrows(UserValidationException.class,
                () -> useCase.savePerson(validPerson));
        List<String> errs = ex.getErrors();
        assertEquals(1, errs.size());
        assertTrue(errs.contains(ValidationConstant.NAME_REQUIRED));
        verifyNoInteractions(persistencePort);
    }

    @Test
    void savePerson_blankName_throwsValidationException() {
        validPerson.setName("   ");
        UserValidationException ex = assertThrows(UserValidationException.class,
                () -> useCase.savePerson(validPerson));
        assertTrue(ex.getErrors().contains(ValidationConstant.NAME_REQUIRED));
        verifyNoInteractions(persistencePort);
    }

    @Test
    void savePerson_invalidEmail_throwsValidationException() {
        validPerson.setEmail("");
        UserValidationException ex = assertThrows(UserValidationException.class,
                () -> useCase.savePerson(validPerson));
        assertTrue(ex.getErrors().contains(ValidationConstant.EMAIL_REQUIRED));
        verifyNoInteractions(persistencePort);
    }

    @Test
    void savePerson_invalidEmail_format_throwsValidationException() {
        validPerson.setEmail("not-an-email");
        UserValidationException ex = assertThrows(UserValidationException.class,
                () -> useCase.savePerson(validPerson));
        assertTrue(ex.getErrors().contains(ValidationConstant.EMAIL_REQUIRED));
        verifyNoInteractions(persistencePort);
    }

    @Test
    void savePerson_multipleErrors_throwsValidationException() {
        validPerson.setName("");
        validPerson.setEmail("bad");
        UserValidationException ex = assertThrows(UserValidationException.class,
                () -> useCase.savePerson(validPerson));
        List<String> errs = ex.getErrors();
        assertEquals(2, errs.size());
        assertTrue(errs.contains(ValidationConstant.NAME_REQUIRED));
        assertTrue(errs.contains(ValidationConstant.EMAIL_REQUIRED));
        verifyNoInteractions(persistencePort);
    }

    @Test
    void getPersonById_callsPersistenceAndReturns() {
        Long id = 42L;
        Person person = new Person();
        when(persistencePort.getPersonById(id)).thenReturn(person);

        Person result = useCase.getPersonById(id);

        assertSame(person, result);
        verify(persistencePort).getPersonById(id);
    }
}