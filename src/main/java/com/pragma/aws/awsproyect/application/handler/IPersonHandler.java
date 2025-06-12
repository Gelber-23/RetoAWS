package com.pragma.aws.awsproyect.application.handler;

import com.pragma.aws.awsproyect.application.dto.request.PersonRequest;
import com.pragma.aws.awsproyect.application.dto.response.PersonResponse;

public interface IPersonHandler {

    void savePerson(PersonRequest personRequest);

    PersonResponse getPersonById(Long id);
}
