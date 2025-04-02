package com.projetMedecine.Modele;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="medecin")
@JsonTypeName("MEDECIN")
@PrimaryKeyJoinColumn(name="id_utilisateur")
@DynamicUpdate
public class Medecin extends Utilisateur {
    Long matricule;
    String specialite;
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "medecin"
    )
    @JsonManagedReference
    private List<Traitement> traitementList = new ArrayList<>();

   @ManyToMany(
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            },
            fetch = FetchType.LAZY
   )
    @JoinTable(
            name="cabinet_medecin",
            joinColumns = @JoinColumn(name="matricule"),
            inverseJoinColumns = @JoinColumn(name = "id_cabinet")
    )
    @JsonManagedReference
    private List<CabinetMedical> cabinetMedicals = new ArrayList<>();
}
