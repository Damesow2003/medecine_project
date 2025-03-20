/*
package com.projetMedecine.Controller;

import com.projetMedecine.Modele.Medecin;
import com.projetMedecine.Repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedecinTestcontroller {
    @Autowired
    private MedecinRepository medecinRepository;

    @GetMapping("/medecins")
    public Iterable<Medecin> getMedecins(){
        Iterable<Medecin> medecins = medecinRepository.findAll();
        return medecins;
    }
}
*/
