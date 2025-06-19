package com.projetMedecine.Modele;

import lombok.Data;

@Data
public class RendezvousPatientDTO {
    private Long id;
    private String dateHeure;
    private int duree;
    //car ici, il y'a pas de relation entre rendezvous et medecin
    /*private String medecinNomComplet;
    private String specialiteMedecin;*/
    private String cabinetNom;
}
