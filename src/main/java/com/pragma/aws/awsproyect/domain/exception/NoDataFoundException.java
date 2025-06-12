package com.pragma.aws.awsproyect.domain.exception;

import com.pragma.aws.awsproyect.domain.util.ValidationConstant;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException() {
        super(ValidationConstant.NO_DATA_FOUND);
    }
}