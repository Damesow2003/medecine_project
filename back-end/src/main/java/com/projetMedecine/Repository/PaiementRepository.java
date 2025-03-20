package com.projetMedecine.Repository;

import com.projetMedecine.Modele.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement,Long> {
    @Modifying
    @Query(value = "UPDATE paiement SET status='PAYER' WHERE id_paiement = :idPaiement", nativeQuery = true)
    int effectuerUnPaiement(@Param("idPaiement") long idPaiement); // retourner le nombre de lignes
}
