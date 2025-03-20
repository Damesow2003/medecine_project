package com.projetMedecine.Controller;

import com.projetMedecine.Exceptions.ExistingPaiementException;
import com.projetMedecine.Exceptions.PaiementImpossibleException;
import com.projetMedecine.Exceptions.PaiementNotFound;
import com.projetMedecine.Exceptions.RendezvousNotFound;
import com.projetMedecine.Modele.Paiement;
import com.projetMedecine.Modele.PaiementProxy;
import com.projetMedecine.Modele.Rendezvous;
import com.projetMedecine.Service.PaiementService;
import com.projetMedecine.Service.RendezVousService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class PaiementController {
    @Autowired
    private PaiementService paiementService;
    @Autowired
    private RendezVousService rendezVousService;
    @GetMapping("/paiements")
    public Iterable<Paiement> getPaiements(){
        return paiementService.paiements();
    }

    @GetMapping("/paiements/{id}")
    public ResponseEntity<Optional<Paiement>> getPaiement(@PathVariable long id){
        Optional<Paiement> existingPaiement = paiementService.paiement(id);

        if(!existingPaiement.isPresent()){
            throw new PaiementNotFound("Le paiement avec l'id "+id+" est introuvable");
        }
        return ResponseEntity.ok(existingPaiement);
    }
    @PostMapping("/paiements")
    public ResponseEntity<Paiement> savePaiement(@Valid @RequestBody PaiementProxy paiementProxy) {
    /*    Optional<Rendezvous> existingPaiement = rendezVousService.getRendezvousById(paiement.getId());

        if(existingPaiement.isPresent()){
            throw new ExistingPaiementException("Paiement deja effectuer");
        }*/
        Paiement nouveauPaiement = paiementService.savePaiement(paiementProxy);
        if(nouveauPaiement == null){
            throw new PaiementImpossibleException("Impossible d'effectuer un paiement pour le moment, Veuillez reesseyer plus tard");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(nouveauPaiement);
    }
    @PutMapping("/paiements/{id}")
    public ResponseEntity<Paiement> updatePaiement(@PathVariable long id,@RequestBody @Valid PaiementProxy paiementProxy){
        Optional<Paiement> existingPaiement = paiementService.paiement(id);
        if(existingPaiement.isEmpty()){
            throw new PaiementNotFound("Le paiement avec l' "+id+" est introuvable");
        }
        Paiement updatePaiement = paiementService.updatePaiement(paiementProxy,id);

        return ResponseEntity.status(HttpStatus.CREATED).body(updatePaiement);
    }

    @PutMapping("/paiements/effectuer-paiement/{id}")
    public ResponseEntity<Paiement> effectuerUnPaiement(@PathVariable long id) throws Exception {
       Paiement validPaiement = paiementService.effectuerPaiement(id);

       return ResponseEntity.ok(validPaiement);
    }
    @DeleteMapping("/paiements/{id}")
    public String deletePaiement(@PathVariable long id){
        paiementService.deletePaiementById(id);
        return "Votre paiement a ete retirer avec success";
    }


}
