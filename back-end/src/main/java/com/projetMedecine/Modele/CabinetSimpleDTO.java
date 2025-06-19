package com.projetMedecine.Modele;

import lombok.Data;

@Data
public class CabinetSimpleDTO {
    private Long idCabinet;
    private String nom;
    private String adresse;

    public CabinetSimpleDTO(Long idCabinet, String adresse, String nom) {
        this.idCabinet = idCabinet;
        this.adresse = adresse;
        this.nom = nom;
    }
    public CabinetSimpleDTO() {}
}
