package com.projetMedecine.Modele;

import lombok.Data;

import java.util.List;

@Data
public class PatientRendezvousDTO {
    private PatientSimpleDTO patient;
    private List<RendezvousPatientCabinetDTO> rendezvous;
}
