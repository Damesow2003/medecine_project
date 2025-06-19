package com.projetMedecine.Modele;

import lombok.Data;

@Data
public class PrescriptionDTO {
    private Long idPrescription;
    private String medicament;
    private String date;
    private RendezvousSimpleDTO simpleRendezvousDTO;
}
