package com.pragma.aws.awsproyect.domain.util;

public class ValidationConstant {

    private ValidationConstant() {
    }

    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public static final String NAME_REQUIRED = "The Name is required";
    public static final String EMAIL_REQUIRED = "The Email is required";

    public static final String NO_DATA_FOUND = "No data found";
}
