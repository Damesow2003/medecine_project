package com.projetMedecine.Repository;

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
}
