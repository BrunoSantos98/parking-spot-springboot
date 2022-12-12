package com.bruno.parkingspot.controller.exceptions;

import com.bruno.parkingspot.services.exceptions.DatabaseException;
import com.bruno.parkingspot.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database (DatabaseException e, HttpServletRequest request){
        String error = "Database Error!";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request
                .getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> conflictData(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Conflito ao criar/atualizar dado";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request
                .getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
