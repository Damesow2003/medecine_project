package com.projetMedecine.Modele;

import lombok.Data;

import java.util.List;

@Data
public class MedecinDTO {
    private Long matricule;
    private String specialite;
    private String prenom;
    private String nom;
    private String email;
    private String telephone;
    private String adresse;
    private String dateDeNaissance;
    private List<CabinetSimpleDTO> cabinets;
    private List<TraitementSimpleDTO> traitements;
}
