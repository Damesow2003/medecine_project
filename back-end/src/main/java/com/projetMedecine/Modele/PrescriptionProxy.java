package com.projetMedecine.Modele;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrescriptionProxy {
    private String medicament;
    private String date;
    private Long idRendezvous;
}