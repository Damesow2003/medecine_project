package com.projetMedecine.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PaiementImpossibleException extends RuntimeException{
    public PaiementImpossibleException(String s){
        super(s);
    }
}
