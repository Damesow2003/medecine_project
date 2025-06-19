package com.projetMedecine.Modele;

import lombok.Data;

@Data
public class TraitementRendezvouSimpleDTO {
    private long idTraitement;
    private String nom;
    private RendezvousSimpleDTO rendezvous;
}
