package com.diegowenndson.springboot.controller.exceptionController;

import com.diegowenndson.springboot.service.Exceptions.DataIntegratyViolationException;
import com.diegowenndson.springboot.service.Exceptions.ObjectNotFoundsExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public ResponseEntity<StandadError> dataIntegratyViolationException(DataIntegratyViolationException err){
        StandadError error = new StandadError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                err.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandadError> methodArgumentNotValidException(MethodArgumentNotValidException err){
        ValidationError error = new ValidationError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Error na validação dos campos!");
        for(FieldError x : err.getBindingResult().getFieldErrors()){
            error.addErrors(x.getField(), x.getDefaultMessage());

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
