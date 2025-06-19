package com.projetMedecine.Service;

import com.projetMedecine.Modele.*;
import com.projetMedecine.Repository.CabinetMedicalRepository;
import com.projetMedecine.Repository.SalleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class SalleService {
    @Autowired
    SalleRepository salleRepository;
    @Autowired
    CabinetMedicalRepository cabinetMedicalRepository;
    @Autowired
    private DtoConversion dtoConversion;
    public List<SalleDTO> salles() {
        return StreamSupport.stream(this.salleRepository.findAll().spliterator(),false)
                .map(/*this::convertToSalleDTO*/ salle->this.dtoConversion.convertToSalleDTO(salle))
                .collect(Collectors.toList());

    }


    public Optional<SalleDTO> salle(long id) {
        return salleRepository.findById(id)
                .map(salle -> this.dtoConversion.convertToSalleDTO(salle));
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
