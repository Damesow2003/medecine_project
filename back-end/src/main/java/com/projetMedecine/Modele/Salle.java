package com.projetMedecine.Modele;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="salle")
@DynamicUpdate
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_salle")
    private Integer idSalle;
    @Column(name="numero_salle")
    private String numeroSalle;
    @Column(name="nom_salle")
    private String nomSalle;

    @ManyToOne(
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            },
            fetch = FetchType.LAZY
    )
    @JoinColumn(name="id_cabinet")
    @JsonBackReference
    private CabinetMedical cabinetMedical;

    @OneToMany(
            fetch= FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "salle"
    )
    @JsonManagedReference
    private List<Traitement> traitements = new ArrayList<>();

}
