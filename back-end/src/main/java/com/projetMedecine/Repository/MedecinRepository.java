package com.projetMedecine.Repository;

import com.projetMedecine.Modele.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin,Long> {
}
