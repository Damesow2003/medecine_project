package com.projetMedecine.Service;

import com.projetMedecine.Modele.*;
import com.projetMedecine.Repository.RendezVousRepository;
import com.projetMedecine.Repository.TraitementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedecinPatientService {
    @Autowired
    private TraitementRepository traitementRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;
    @Autowired
    private DtoConversion dtoConversion;

    public List<TraitementDTO> getTraitementsByMedecin(Long matricule) {
        return traitementRepository.findByMedecinMatriculeWithPatient(matricule)
                .stream()
                .map(t -> {
                    TraitementDTO dto = dtoConversion.convertToTraitementDTO(t);
                    if (t.getPatient() != null) {
                        dto.setPatient(dtoConversion.convertToPatientSimpleDTO(t.getPatient()));
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<RendezvousDTO> getRendezvousByMedecin(Long matricule,Long cabinetId){
        List<Rendezvous> rendevous = rendezVousRepository.findByMedecinMatriculeAndCabinetMedical(matricule,cabinetId);
            return rendevous.stream()
                .map(rendezvous->this.dtoConversion.convertToRendezvousDTO(rendezvous))
                .collect(Collectors.toList());
    }
}
