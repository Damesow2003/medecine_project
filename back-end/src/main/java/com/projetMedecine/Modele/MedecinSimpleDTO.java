package com.projetMedecine.Modele;

import lombok.Data;

@Data
public class MedecinSimpleDTO {
    private Long matricule;
    private String specialite;
    private String nom;
    private String prenom;
}
