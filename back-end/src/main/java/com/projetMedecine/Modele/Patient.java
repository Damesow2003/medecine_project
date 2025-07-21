package com.projetMedecine.Modele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;
import java.util.ArrayList;

@Entity
@DynamicUpdate
@Table(name = "patient")
@Data
@JsonTypeName("PATIENT")
@PrimaryKeyJoinColumn(name="id_utilisateur")
public class Patient  extends Utilisateur{

   @Column(name="id_patient")
    private long idPatient;
  @OneToOne(
          mappedBy = "patient",
          cascade = CascadeType.ALL,
          fetch = FetchType.EAGER,
          orphanRemoval = true
  )
  @JsonBackReference
  private Rendezvous rendezvous;

  @OneToMany(
          mappedBy = "patient",
          fetch = FetchType.LAZY,
          cascade = {
                  CascadeType.MERGE,
                  CascadeType.PERSIST
          }
  )
  @JsonManagedReference
  private List<Traitement> traitements = new ArrayList<>();

  public Long getIdPatient() {
       return this.idPatient;
   }

}
