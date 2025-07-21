package com.projetMedecine.Repository;

import com.projetMedecine.Modele.Medecin;
import com.projetMedecine.Modele.MedecinCabinetDTO;
import com.projetMedecine.Modele.MedecinDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    Medecin findByMatricule(Long matricule);
    List<Medecin> findMedecinByRole(String role);
  /*  @Query(value="SELECT * from medecin JOIN medecin_cabinet ON
            medecin_cabinet.matricule = :medecin.matricule
    JOIN cabinet_medical ON medecin_cabinet.id_cabinet = cabinet_medical.id_cabinet ;",nativeQuery = true)
*/

    @Query("SELECT DISTINCT m FROM Medecin m JOIN m.traitementList t WHERE t.patient.idPatient = :idPatient")
    List<Medecin> findMedecinByPatientId(@Param("idPatient") Long idPatient);

}
