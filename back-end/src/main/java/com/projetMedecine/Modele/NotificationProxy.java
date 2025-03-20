package com.projetMedecine.Modele;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NotificationProxy {
    private String contenu;
    private LocalDate dateEnvoie;
    private Long idRendezvous;
}
