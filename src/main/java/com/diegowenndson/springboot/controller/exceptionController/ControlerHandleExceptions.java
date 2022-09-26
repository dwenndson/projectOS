package com.diegowenndson.springboot.controller.exceptionController;

import com.diegowenndson.springboot.service.Exceptions.DataIntegratyViolationException;
import com.diegowenndson.springboot.service.Exceptions.ObjectNotFoundsExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControlerHandleExceptions {

    @ExceptionHandler(ObjectNotFoundsExceptions.class)
    public ResponseEntity<StandadError> objectNotFoundsExceptions(ObjectNotFoundsExceptions err){
        StandadError error = new StandadError(System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                err.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegratyViolationException.class)
    public ResponseEntity<StandadError> objectNotFoundsExceptions(DataIntegratyViolationException err){
        StandadError error = new StandadError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                err.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
