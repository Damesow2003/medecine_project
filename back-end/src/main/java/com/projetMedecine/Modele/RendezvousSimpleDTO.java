package com.projetMedecine.Modele;

import lombok.Data;

@Data
public class RendezvousSimpleDTO {
    private Long id;
    private String dateHeure;
    private int duree;
    private String patientNomComplet;

}
