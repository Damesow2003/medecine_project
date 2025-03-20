package com.projetMedecine.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PrescriptionBadRequest extends RuntimeException{
    public PrescriptionBadRequest(String s){
        super(s);
    }
}
