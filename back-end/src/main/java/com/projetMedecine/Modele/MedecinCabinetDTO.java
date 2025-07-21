package com.projetMedecine.Modele;

import lombok.Data;

@Data
public class MedecinCabinetDTO {
    private Long idUtilisateur;
    private Long matricule;
    private String specialite;
    private Long idCabinet;
    private String adresse;
    private String nom;
    private String image;
}
