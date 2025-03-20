package com.projetMedecine.Modele;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CabinetRendezvous {
   private String adresse;
   private String nom;
   RendezvousProxy rendezvousProxy;

    public CabinetRendezvous(String adresse, String nom, RendezvousProxy rendezvousProxy) {
        this.adresse = adresse;
        this.nom = nom;
        this.rendezvousProxy = rendezvousProxy;
    }
}
