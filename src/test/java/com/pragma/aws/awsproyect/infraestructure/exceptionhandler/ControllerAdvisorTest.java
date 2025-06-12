package com.pragma.aws.awsproyect.infraestructure.exceptionhandler;

import com.pragma.aws.awsproyect.domain.exception.NoDataFoundException;
import com.pragma.aws.awsproyect.domain.exception.UserValidationException;
import com.pragma.aws.awsproyect.domain.util.ValidationConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ControllerAdvisorTest {
    private ControllerAdvisor advisor;
    private static final String MESSAGE = "Message";

    @BeforeEach
    void setUp() {
        advisor = new ControllerAdvisor();
    }

    @Test
    void handleNoDataFound_shouldReturnNotFound_andMessage() {
        NoDataFoundException ex = new NoDataFoundException();
        ResponseEntity<Map<String, String>> resp = advisor.handleNoDataFound(ex);
        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
        assertEquals(ValidationConstant.NO_DATA_FOUND, Objects.requireNonNull(resp.getBody()).get(MESSAGE));
    }

    @Test
    void handlePersonValidation_shouldReturnBadRequest_andErrorsList() {
        List<String> errs = List.of("e1", "e2");
        UserValidationException ex = new UserValidationException(errs);
        ResponseEntity<Map<String, List<String>>> resp = advisor.handlePersonValidation(ex);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        assertEquals(errs, Objects.requireNonNull(resp.getBody()).get(MESSAGE));
    }

    @Test
    void handleValidationErrors_shouldReturnBadRequest_andFieldErrors() throws Exception {
        BeanPropertyBindingResult binding = new BeanPropertyBindingResult(this, "obj");
        binding.addError(new FieldError("obj", "fieldA", "msgA"));
        binding.addError(new FieldError("obj", "fieldB", "msgB"));
        Method m = ControllerAdvisor.class
                .getDeclaredMethod("handleValidationErrors", MethodArgumentNotValidException.class);
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(
                new org.springframework.core.MethodParameter(m, 0), binding);

        ResponseEntity<Map<String, String>> resp = advisor.handleValidationErrors(ex);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Map<String, String> body = resp.getBody();
        assert body != null;
        assertEquals(2, body.size());
        assertEquals("msgA", body.get("fieldA"));
        assertEquals("msgB", body.get("fieldB"));
    }

}