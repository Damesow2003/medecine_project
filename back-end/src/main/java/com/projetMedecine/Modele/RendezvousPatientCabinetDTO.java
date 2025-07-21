package com.projetMedecine.Modele;

import lombok.Data;

@Data
public class RendezvousPatientCabinetDTO {
    private Long id;
    private String dateHeure;
    private int duree;
    private String status; //A_VENIR, EN_COURS, PASSE
    private CabinetSimpleDTO cabinet;
    private MedecinSimpleDTO medecin;

    public void setStatut(String s) {
        this.status = s;
    }
}
