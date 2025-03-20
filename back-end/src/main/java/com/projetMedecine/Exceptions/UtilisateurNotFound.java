package com.projetMedecine.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UtilisateurNotFound extends RuntimeException {
    public UtilisateurNotFound(String s){
        super(s);
    }
}
