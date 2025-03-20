package com.projetMedecine.Modele;

import lombok.Data;

@Data
public class TraitementProxy {
    private String nom;
    private long idSalle;
    private int idPatient;
    private long matriculeMedecin;
    private long idRendezvous;
}
