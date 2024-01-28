package com.example.s23319.exceptions;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Map;

@Getter
@Setter
public class ValidationException extends RuntimeException{
    private String field;
    private String message;
    @Getter
    private Map<String, String> errors;
    public String GetMessageWithField() {return field + " " + message;}

    public ValidationException(String field, String message){
        super();
        this.message = message;
        this.field = field;
    }
    public ValidationException(Map<String, String> errors) {this.errors = errors;}
}
