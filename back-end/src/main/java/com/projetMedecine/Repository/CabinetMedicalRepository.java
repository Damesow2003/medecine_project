package com.projetMedecine.Repository;

import com.projetMedecine.Modele.Admin;
import com.projetMedecine.Modele.CabinetMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinetMedicalRepository extends JpaRepository<CabinetMedical,Long> {
    CabinetMedical findCabinetMedicalByAdmin_Username(String adminUsername);
}
