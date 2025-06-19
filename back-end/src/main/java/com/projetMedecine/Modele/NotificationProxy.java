package com.projetMedecine.Modele;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NotificationProxy {
    private String contenu;
    private String dateEnvoie;
    private Long idRendezvous;
}
