package com.projetMedecine.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaiementNotFound extends RuntimeException{
    public PaiementNotFound(String s){
        super(s);
    }
}
