package com.projetMedecine.Modele;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrescriptionProxy {
    private String medicament;
    private LocalDate date;
    private Long idRendezvous;
}