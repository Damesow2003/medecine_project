package com.projetMedecine.Service;

import com.projetMedecine.Modele.*;
import com.projetMedecine.Repository.CabinetMedicalRepository;
import com.projetMedecine.Repository.RendezVousRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class CabinetMedicalService {
    @Autowired
    private CabinetMedicalRepository cabinetMedicalRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;
    @Autowired
    private DtoConversion dtoConversion;

    public List<CabinetMedicalDTO> getCabinetMedicals()  {
        return StreamSupport.stream(cabinetMedicalRepository.findAll().spliterator(),false)
                .map(/*this::convertToCabinetMedicalDTO*/cabinet-> this.dtoConversion.convertToCabinetMedicalDTO(cabinet))
                .collect(Collectors.toList());
    }

    public Optional<CabinetMedicalDTO> getCabinetMedical(long id) {
       return cabinetMedicalRepository.findById(id)
               .map(/*this::convertToCabinetMedicalDTO*/ cabinet -> this.dtoConversion.convertToCabinetMedicalDTO(cabinet));

    }

    public CabinetMedical saveCabinetMedical(CabinetMedicalProxy cabinetMedicalProxy) {
        CabinetMedical newCabinetMedical = new CabinetMedical();
        newCabinetMedical.setAdresse(cabinetMedicalProxy.getAdresse());
        newCabinetMedical.setNom(cabinetMedicalProxy.getNom());
        newCabinetMedical.setImageUrl(cabinetMedicalProxy.getImageUrl());
        //Association avec Rendezvous(sa permet l'ajout de la clef etrangeres)
      /*  if (cabinetMedicalProxy.getIdCabinetMedical() != null) {
            List<Rendezvous> rendezvousList = rendezVousRepository.findAllById(cabinetMedicalProxy.getIdCabinetMedical());
            //newCabinetMedical.setRendezvous(rendezvousList);
            newCabinetMedical.setRendezvousList(rendezvousList);
        }*/
        return cabinetMedicalRepository.save(newCabinetMedical);
    }
    public CabinetMedical updateCabinetMedical(long id,CabinetMedicalProxy cabinetMedicalProxy){
        Optional<CabinetMedical> existingCabinet = cabinetMedicalRepository.findById(id);
        CabinetMedical updatedCabinetMedical = existingCabinet.get();
       updatedCabinetMedical.setAdresse(cabinetMedicalProxy.getAdresse());
       updatedCabinetMedical.setNom(cabinetMedicalProxy.getNom());
       updatedCabinetMedical.setImageUrl(cabinetMedicalProxy.getImageUrl());

       return cabinetMedicalRepository.save(updatedCabinetMedical);
    }
    public void deleteCabinetMedical(long id){
        cabinetMedicalRepository.deleteById(id);
    }

    public CabinetMedical getCabinetMedicalByadminUsername(String username) {
            return cabinetMedicalRepository.findCabinetMedicalByAdmin_Username(username);
    }
}
