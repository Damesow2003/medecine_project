package com.projetMedecine.Service;

import com.projetMedecine.Modele.*;
import com.projetMedecine.Repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MedecinService {
    @Autowired
   private  MedecinRepository medecinRepository;
    @Autowired
    private DtoConversion dtoConversion;

    public List<MedecinDTO> getAllMedecin(){
        return StreamSupport.stream(medecinRepository.findAll().spliterator(),false)
                .map(/*this::convertToMedecinDTO*/ medecin -> this.dtoConversion.convertToMedecinDTO(medecin))
                .collect(Collectors.toList());
    }
    public List<MedecinDTO> getMedecinByRole(String role) {
        return medecinRepository.findMedecinByRole(role).stream()
                .map(medecin -> this.dtoConversion.convertToMedecinDTO(medecin))
                .collect(Collectors.toList());
    }

    public Optional<MedecinDTO> getMedecinByMatricule(Long matricule){
      return medecinRepository.findById(matricule).map(medecin -> this.dtoConversion.convertToMedecinDTO(medecin));
    }

    public Medecin savedMedecin(Medecin saveMedecin){
        return medecinRepository.save(saveMedecin);
    }


}
