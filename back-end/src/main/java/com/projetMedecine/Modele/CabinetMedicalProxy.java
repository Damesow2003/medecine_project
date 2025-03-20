package com.projetMedecine.Modele;

import lombok.Data;

import java.util.List;

@Data
public class CabinetMedicalProxy {
    private String adresse;
    private String nom;
    private String imageUrl;
    //private List<Long> idCabinetMedical;


    public CabinetMedicalProxy(String adresse, String nom,String imageUrl) {
        this.adresse = adresse;
        this.nom = nom;
        this.imageUrl = imageUrl;
    }
}
