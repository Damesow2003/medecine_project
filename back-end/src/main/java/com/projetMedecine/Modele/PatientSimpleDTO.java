package com.projetMedecine.Modele;

import lombok.Data;

@Data
public class PatientSimpleDTO {
    private Long idPatient;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;;
    private String telephone;
    private String dateDeNaissance;
}
