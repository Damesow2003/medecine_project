package com.projetMedecine.Service;

import com.projetMedecine.Modele.CabinetMedical;
import com.projetMedecine.Modele.Salle;
import com.projetMedecine.Modele.SalleProxy;
import com.projetMedecine.Repository.CabinetMedicalRepository;
import com.projetMedecine.Repository.SalleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class SalleService {
    @Autowired
    SalleRepository salleRepository;
    @Autowired
    CabinetMedicalRepository cabinetMedicalRepository;

    public Iterable<Salle> salles() {
        return salleRepository.findAll();
    }

    public Optional<Salle> salle(long id) {
        return salleRepository.findById(id);
    }

    public Salle saveSalle(SalleProxy salleProxy) {
        Salle salle = new Salle();
        salle.setNumeroSalle(salleProxy.getNumeroSalle());
        salle.setNomSalle(salleProxy.getNomSalle());

        if (salleProxy.getIdCabinet() != null) {
            Optional<CabinetMedical> existingCabinet = Optional.ofNullable(cabinetMedicalRepository.findById(salleProxy.getIdCabinet())
                    .orElseThrow(() -> new RuntimeException("Cabinet not found")));
            salle.setCabinetMedical(existingCabinet.get());
        }
        return salleRepository.save(salle);
    }

    public Salle updateSalle(SalleProxy salleProxy, long id) {
        Optional<Salle> existingSalle = salleRepository.findById(id);
        Salle updatedSalle = existingSalle.get();
        updatedSalle.setNumeroSalle(salleProxy.getNumeroSalle());
        updatedSalle.setNomSalle(salleProxy.getNomSalle());
        if (salleProxy.getIdCabinet() != null) {
            Optional<CabinetMedical> existingCabinet = cabinetMedicalRepository.findById(salleProxy.getIdCabinet());
            updatedSalle.setCabinetMedical(existingCabinet.get());
        }
        return salleRepository.save(updatedSalle);
    }

    public void deleteSalle(long id) {
        salleRepository.deleteById(id);
    }
}
