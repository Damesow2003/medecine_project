package com.projetMedecine.Modele;

import lombok.Data;

@Data
public class TraitementDTO {
    private Long idTraitement;
    private String nom;
    private PatientSimpleDTO patient;
    private SalleSimpleDTO salle;
    private MedecinSimpleDTO medecin;
}
