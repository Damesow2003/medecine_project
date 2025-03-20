package com.projetMedecine.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImpossibleAjouterUtilisateur extends RuntimeException{
    public ImpossibleAjouterUtilisateur(String s){
        super(s);
    }
}