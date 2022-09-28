package com.diegowenndson.springboot.controller.exceptionController;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandadError{
    private static final long serialVersionUID = 1L;

    private List<FieldMessade> errors = new ArrayList<>();

    public ValidationError(long l, int value, String s) {
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error, List<FieldMessade> errors) {
        super(timestamp, status, error);
    }

    public List<FieldMessade> getErrors() {
        return errors;
    }

    public void addErrors(String fieldName, String message) {
        this.errors.add(new FieldMessade(fieldName, message));
    }
}
