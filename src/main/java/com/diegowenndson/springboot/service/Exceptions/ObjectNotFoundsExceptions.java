package com.diegowenndson.springboot.service.Exceptions;

public class ObjectNotFoundsExceptions extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundsExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotFoundsExceptions(String message) {
        super(message);
    }


}
