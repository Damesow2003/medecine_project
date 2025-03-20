package com.projetMedecine.Repository;

import com.projetMedecine.Modele.Rendezvous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<Rendezvous,Long> {
    List<Rendezvous> findRendezvousByCabinetMedical_IdCabinet(Long idCabinet);

    @Modifying
    @Query(value = "UPDATE rendezvous SET id_paiement = :idPaiement WHERE id_rendezvous = :idRendezvous", nativeQuery = true)
    int paiementEffectuerSurRendezvous(@Param("idPaiement") Long idPaiement, @Param("idRendezvous") Long idRendezvous);
}
