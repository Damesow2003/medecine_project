package com.projetMedecine.Service;

import com.projetMedecine.Modele.Utilisateur;
import com.projetMedecine.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;



    public Utilisateur savedUser(Utilisateur user){
        return utilisateurRepository.save(user);
    }
    public Utilisateur recuperCompte(String email){
        return utilisateurRepository.findByEmail(email);
    }
}
