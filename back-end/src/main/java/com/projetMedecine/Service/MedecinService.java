package com.projetMedecine.Service;

import com.projetMedecine.Modele.Medecin;
import com.projetMedecine.Repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedecinService {
    @Autowired
   private  MedecinRepository medecinRepository;

    public Iterable<Medecin> getAllMedecin(){
        return medecinRepository.findAll();
    }

    public Medecin savedMedecin(Medecin saveMedecin){
        return medecinRepository.save(saveMedecin);
    }

}
