package com.projetMedecine.Modele;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RendezvousProxy {
    private Long id;
    private String dateRv;
    private String heureRv;
    private int duree;
    //private List<Long> idNotification;
    //private List<Long> idPrescription;
    //private Long idPaiement;

    private Long idCabinet;

    public RendezvousProxy(Long id,String dateRv, String heureRv, int duree, Long idCabinet) {
        this.id = id;
        this.dateRv = dateRv;
        this.heureRv = heureRv;
        this.duree = duree;
        //this.idPaiement = idPaiement;
        this.idCabinet = idCabinet;
    }
}
