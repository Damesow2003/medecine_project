package com.projetMedecine.Modele;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
@JsonTypeName("ADMIN")
@PrimaryKeyJoinColumn(name="id_utilisateur")
@Table(name = "admin")
public class Admin extends Utilisateur{

    @Column(name="id_admin")
    private Long idAdmin;
    @OneToOne(
            fetch= FetchType.EAGER
    )
    @JoinColumn(name="id_cabinet")
    @JsonBackReference
    private CabinetMedical cabinetMedical;
}
