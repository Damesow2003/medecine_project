package com.projetMedecine.Modele;

import lombok.Data;

import java.util.List;

@Data
public class CabinetMedicalDTO {
    private Long idCabinet;
    private String adresse;
    private String nom;
    private String imageUrl;
    private List<SalleSimpleDTO> salles;
    private List<MedecinSimpleDTO> medecins;
    private List<RendezvousSimpleDTO> rendezvous;
}
