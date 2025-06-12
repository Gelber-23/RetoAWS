package com.pragma.aws.awsproyect.application.mapper.response;

import com.pragma.aws.awsproyect.application.dto.response.PersonResponse;
import com.pragma.aws.awsproyect.domain.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE )
public interface IPersonResponseMapper {

    PersonResponse toResponse(Person person);
}
