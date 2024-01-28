package com.example.s23319.controller;


import com.example.s23319.exceptions.ClientNotFoundException;
import com.example.s23319.exceptions.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClientControllerAdvice {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationexception(ValidationException e){
        ErrorResponse errorResponse = new ErrorResponse(e.GetMessageWithField());
        if(!e.getErrors().isEmpty()){
            errorResponse = new ErrorResponse("Validation error", e.getErrors());
        }
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
