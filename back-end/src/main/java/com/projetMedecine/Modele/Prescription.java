package com.projetMedecine.Modele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "prescription")
@DynamicUpdate
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_prescription")
    Long idPrescription;
    private String medicament;
    private LocalDate date;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JsonBackReference
    @JoinColumn(name="id_rendezvous")
    private Rendezvous rendezvous;
}
