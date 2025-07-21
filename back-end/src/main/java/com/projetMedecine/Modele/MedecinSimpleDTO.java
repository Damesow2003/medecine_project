package com.projetMedecine.Modele;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MedecinSimpleDTO {
    private Long matricule;
    private String specialite;
    private String nom;
    private String prenom;

   public MedecinSimpleDTO() {

   }
   public MedecinSimpleDTO(Long matricule, String specialite, String nom, String prenom) {
       this.matricule = matricule;
       this.specialite = specialite;
       this.nom = nom;
       this.prenom = prenom;
   }

}
