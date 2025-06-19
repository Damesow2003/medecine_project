package com.projetMedecine.Modele;

import lombok.Data;

@Data
public class NotificationDTO {
    private Long idNotification;
    private String contenu;
    private String dateEnvoie;
    private RendezvousSimpleDTO rendezvousSimpleDTO;
}
