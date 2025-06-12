package com.pragma.aws.awsproyect.infraestructure.out.jpa.repository;

import com.pragma.aws.awsproyect.infraestructure.out.jpa.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<PersonEntity, Long> {
}
