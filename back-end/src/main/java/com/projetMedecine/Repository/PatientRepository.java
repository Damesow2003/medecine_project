package com.projetMedecine.Repository;

import com.projetMedecine.Modele.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
   /* @Query(value = "SELECT * FROM Patient p WHERE p.id_patient= :idTraitement",nativeQuery = true)
    List<Patient> findPatientByTraitement(@Param("idTraitement") Long idTraitement);*/
}
