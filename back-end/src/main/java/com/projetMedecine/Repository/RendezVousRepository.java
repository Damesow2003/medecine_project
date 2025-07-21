package com.projetMedecine.Repository;

import com.projetMedecine.Modele.Rendezvous;
import com.projetMedecine.Modele.RendezvousPatientDTO;
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


    @Query("SELECT r FROM Rendezvous  r JOIN FETCH r.patient WHERE r.cabinetMedical.idCabinet= :cabinetId AND r.traitement.medecin.matricule = :matricule")
    List<Rendezvous> findByMedecinMatriculeAndCabinetMedical(@Param("matricule") Long matricule, @Param("cabinetId") Long cabinetId);

    @Query(value = """
        SELECT r.* FROM rendezvous r 
        WHERE r.id_patient = :idPatient 
        AND STR_TO_DATE(CONCAT(r.date_rv, ' ', r.heure_rv), '%Y-%m-%d %H:%i:%s') < NOW()
        """, nativeQuery = true)
    List<Rendezvous> findPastRendezvousByPatient(@Param("idPatient") Long idPatient);

    @Query(value = """
        SELECT r.* FROM rendezvous r 
        WHERE r.id_patient = :idPatient 
        AND NOW() BETWEEN 
            STR_TO_DATE(CONCAT(r.date_rv, ' ', r.heure_rv), '%Y-%m-%d %H:%i:%s')
            AND DATE_ADD(
                STR_TO_DATE(CONCAT(r.date_rv, ' ', r.heure_rv), '%Y-%m-%d %H:%i:%s'), 
                INTERVAL r.duree MINUTE
            )
        """, nativeQuery = true)
    List<Rendezvous> findCurrentRendezvousByPatient(@Param("idPatient") Long idPatient);

    @Query("SELECT r FROM Rendezvous r WHERE r.patient.idPatient = :idPatient ORDER BY r.dateRv DESC, r.heureRv DESC")
    List<Rendezvous> findAllRendezvousByPatientOrderByDate(@Param("idPatient") Long idPatient);

/*    List<Rendezvous> findAllRendezvousByPatient(Long idPatient);*/

    List<Rendezvous> findByPatientId(Long idPatient);
}
