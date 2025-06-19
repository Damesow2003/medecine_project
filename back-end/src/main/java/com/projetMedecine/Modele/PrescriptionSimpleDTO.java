package com.projetMedecine.Modele;

import lombok.Data;

@Data
public class PrescriptionSimpleDTO {
    private Long idPrescription;
    private String medicament;
    private String date;
}
