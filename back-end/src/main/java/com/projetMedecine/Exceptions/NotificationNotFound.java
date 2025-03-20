package com.projetMedecine.Exceptions;

import com.projetMedecine.Modele.Notification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotificationNotFound extends RuntimeException{
    public NotificationNotFound(String s){
        super(s);
    }
}
