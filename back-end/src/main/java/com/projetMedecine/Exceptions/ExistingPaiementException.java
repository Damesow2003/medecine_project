package com.projetMedecine.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExistingPaiementException extends RuntimeException{
    public ExistingPaiementException(String s){
        super(s);
    }
}
