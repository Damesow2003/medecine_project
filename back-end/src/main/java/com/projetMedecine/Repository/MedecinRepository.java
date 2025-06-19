package com.projetMedecine.Repository;

import com.projetMedecine.Modele.Medecin;
import com.projetMedecine.Modele.MedecinDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    Medecin findByMatricule(Long matricule);
}
