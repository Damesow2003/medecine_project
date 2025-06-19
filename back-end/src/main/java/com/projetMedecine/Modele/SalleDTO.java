package com.projetMedecine.Modele;

import lombok.Data;

import java.util.List;

@Data
public class SalleDTO {
    Long idSalle;
    String numeroSalle;
    String nomSalle;
    String status;
    CabinetSimpleDTO cabinet;
    List<TraitementRendezvouSimpleDTO> traitements;
}
