package com.pragma.aws.awsproyect.application.handler.impl;

import com.pragma.aws.awsproyect.application.dto.request.PersonRequest;
import com.pragma.aws.awsproyect.application.dto.response.PersonResponse;
import com.pragma.aws.awsproyect.application.handler.IPersonHandler;
import com.pragma.aws.awsproyect.application.mapper.request.IPersonRequestMapper;
import com.pragma.aws.awsproyect.application.mapper.response.IPersonResponseMapper;
import com.pragma.aws.awsproyect.domain.api.IPersonServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class PersonHandler implements IPersonHandler {
    private final IPersonServicePort personServicePort;
    private final IPersonResponseMapper personResponseMapper;
    private final IPersonRequestMapper personRequestMapper;
    @Override
    public void savePerson(PersonRequest personRequest) {
        personServicePort.savePerson(personRequestMapper.toPerson(personRequest));
    }

    @Override
    public PersonResponse getPersonById(Long id) {
        return personResponseMapper.toResponse(personServicePort.getPersonById(id));
    }
}
