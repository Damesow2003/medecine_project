package com.projetMedecine.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RendezvousNotFound extends RuntimeException{
    public RendezvousNotFound(String s){
        super(s);
    }
}
