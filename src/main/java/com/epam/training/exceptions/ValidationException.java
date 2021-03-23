package com.epam.training.exceptions;

import java.util.Arrays;
import java.util.List;

public class ValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    private final List<String> errors;

    /**
     * @param errors list of errors cause the exception
     */
    public ValidationException(final String ... errors) {
        this.errors = Arrays.asList(errors);
    }

    /**
     *
     * @return list of validation errors
     */
    public List<String> getErrors() {
        return errors;
    }
}
