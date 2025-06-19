package com.projetMedecine.Modele;


import lombok.Data;

@Data
public class PaiementDTO {
    private Long idPaiement;
    private String modeDePaiement;
    private Double montant;
    private String status;
}