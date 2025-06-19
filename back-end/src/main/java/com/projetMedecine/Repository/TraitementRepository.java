package com.projetMedecine.Repository;

import com.projetMedecine.Modele.Medecin;
import com.projetMedecine.Modele.Traitement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraitementRepository extends JpaRepository<Traitement,Long> {
    @Query(value = "SELECT * FROM traitement t WHERE t.id_patient= :idPatient",nativeQuery = true)
    List<Traitement> findTraitementByIdPatient(@Param("idPatient") Long idPatient);

    // Alternative si vous voulez quand même récupérer les infos patient via le rendez-vous
    @Query("SELECT t FROM Traitement t JOIN FETCH t.rendezvous r JOIN FETCH r.patient WHERE t.medecin.matricule = :matricule")
    List<Traitement> findByMedecinMatriculeWithPatientViaRendezvous(@Param("matricule") Long matricule);

    List<Traitement> findByMedecinMatricule(Long matricule);

    @Query(value = """
        SELECT t.*, p.* 
        FROM traitement t 
        LEFT JOIN patient p ON t.id_patient = p.id_patient 
        WHERE t.matricule_medecin = :matricule
        """, nativeQuery = true)
    List<Object[]> findTraitementsAndPatientsByMedecin(@Param("matricule") Long matricule);

    @Query("SELECT t FROM Traitement t LEFT JOIN FETCH t.patient WHERE t.medecin.matricule = :matricule")
    List<Traitement> findByMedecinMatriculeWithPatient(@Param("matricule") Long matricule);
}
