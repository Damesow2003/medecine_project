package com.projetMedecine.Modele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="traitement")
@Data
@DynamicUpdate
public class Traitement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_traitement")
    private Long idTraitement;
    //nouvelle modification de la modele j'ai ajoute une attribut nom du traitement
    private String nom;
    @Column(name = "id_patient")
    private int idPatient;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            }
    )
    @JsonBackReference
    @JoinColumn(name = "id_salle")
    private Salle salle;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JsonBackReference
    @JoinColumn(name="matricule_medecin")
    private Medecin medecin;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    @JoinColumn(name="id_rendezvous")
    private Rendezvous rendezvous;

}
