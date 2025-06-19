package com.projetMedecine.Modele;

import lombok.Data;

import java.util.List;

@Data
public class PatientDTO {
    private Long idPatient;
    private String nom;
    private String prenom;
    private String email;
    private String dateDeNaissance;
    private String adresse;
    private String role;
    private RendezvousPatientDTO rendezvous;
    private TraitementSimpleDTO traitementSimple;
}