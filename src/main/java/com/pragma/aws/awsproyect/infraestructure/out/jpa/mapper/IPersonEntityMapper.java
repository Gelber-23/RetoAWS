package com.pragma.aws.awsproyect.infraestructure.out.jpa.mapper;

import com.pragma.aws.awsproyect.domain.model.Person;
import com.pragma.aws.awsproyect.infraestructure.out.jpa.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE )
public interface IPersonEntityMapper {
    PersonEntity toEntity(Person person);

    Person toUserModel(PersonEntity personEntity);
}
