package com.projetMedecine.Modele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Data
@Table(name="notification")
@DynamicUpdate
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_notification")
    Long idNotification;
    private String contenu;
    @Column(name="date_envoie")
    private LocalDate dateEnvoie;

    @ManyToOne(
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
            },
            fetch = FetchType.LAZY
    )
    @JsonBackReference
    @JoinColumn(name="id_rendezvous")
    private Rendezvous rendezvous;
}
