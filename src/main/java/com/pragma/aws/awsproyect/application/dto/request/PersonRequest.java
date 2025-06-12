package com.pragma.aws.awsproyect.application.dto.request;

import com.pragma.aws.awsproyect.domain.util.ValidationConstant;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRequest {


    @NotBlank(message = ValidationConstant.NAME_REQUIRED)
    private String name;


    @NotBlank(message =  ValidationConstant.EMAIL_REQUIRED)
    @Email(message = ValidationConstant.EMAIL_REQUIRED)
    private String email;

}
