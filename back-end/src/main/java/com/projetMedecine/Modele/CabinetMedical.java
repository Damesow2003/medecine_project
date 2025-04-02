package com.projetMedecine.Modele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cabinet_medical")
@Data
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@DynamicUpdate
public class CabinetMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cabinet")
    private Long idCabinet;
    private String adresse;
    private String nom;
    @Column(name="image")
    private String imageUrl;

    @ManyToMany(mappedBy = "cabinetMedicals")
    @JsonBackReference
    private List<Medecin> medecins = new ArrayList<>();


   @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "cabinetMedical"
   )
    @JsonManagedReference
     List<Salle> salles = new ArrayList<>();
   @OneToMany(
           cascade = CascadeType.ALL,
           fetch = FetchType.LAZY,
          mappedBy = "cabinetMedical"
   )
   @JsonManagedReference
   //@JoinColumn(name="id_cabinet")
    private List<Rendezvous> rendezvousList = new ArrayList<>();

   @OneToOne(
           cascade = CascadeType.ALL,
           fetch = FetchType.EAGER,
           mappedBy = "cabinetMedical"
   )
    @JsonManagedReference
    private Admin admin;
}
