package com.pragma.aws.awsproyect.infraestructure.configuration;

import com.pragma.aws.awsproyect.domain.api.IPersonServicePort;
import com.pragma.aws.awsproyect.domain.spi.IPersonPersistencePort;
import com.pragma.aws.awsproyect.domain.usecase.PersonUseCase;
import com.pragma.aws.awsproyect.infraestructure.out.jpa.adapter.PersonJpaAdapter;
import com.pragma.aws.awsproyect.infraestructure.out.jpa.mapper.IPersonEntityMapper;
import com.pragma.aws.awsproyect.infraestructure.out.jpa.repository.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {


    private final IPersonRepository personRepository;
    private final IPersonEntityMapper personEntityMapper;


    @Bean
    public IPersonPersistencePort personPersistencePort(){
        return new PersonJpaAdapter(personRepository, personEntityMapper);
    }


    @Bean
    public IPersonServicePort personServicePort(){
        return new PersonUseCase(personPersistencePort());
    }

}
