package com.projetMedecine.Modele;

import lombok.Data;

import java.util.List;

@Data
public class RendezvousDTO {
    private Long id;
    private String dateRv;
    private String heureRv;
    private int duree;
    private PaiementDTO paiement;
    private List<NotificationDTO> notifications;
    private List<PrescriptionDTO> prescriptions;
    private PatientSimpleDTO patient; // Version simplifiée du patient
    private CabinetSimpleDTO cabinet;
    //private MedecinSimpleDTO medecin;
}