package com.projetMedecine.Controller;

import com.projetMedecine.Exceptions.RendezvousBadRequest;
import com.projetMedecine.Exceptions.RendezvousNotFound;
import com.projetMedecine.Exceptions.RendezvousServerError;
import com.projetMedecine.Modele.*;
import com.projetMedecine.Service.NotificationService;
import com.projetMedecine.Service.PaiementService;
import com.projetMedecine.Service.PrescriptionService;
import com.projetMedecine.Service.RendezVousService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.List;

@RestController
@CrossOrigin("*")
public class RendezVousController {
    @Autowired
    private RendezVousService rendezVousService;
    @Autowired
    private PaiementService paiementService;
    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/rendezvous")
    public Iterable<Rendezvous> getRendezvous(){
        return rendezVousService.listRendezvous();
    }
    @GetMapping("/rendezvous/{id}")
    public ResponseEntity<Optional> getOnlyRendezvous(@PathVariable Long id){
        Optional<Rendezvous> rendezvous = rendezVousService.getRendezvousById(id);

        if(rendezvous.isEmpty()){
            throw new RendezvousNotFound("Le rendezvous avec l' id"+id+" est introuvable");
        }
        return ResponseEntity.ok(rendezvous);
    }
    @PostMapping("/rendezvous")
    public ResponseEntity<Rendezvous> saveRendezvous(@RequestBody RendezvousProxy rendezvousProxy){
        if(rendezvousProxy ==null){
            throw new RendezvousBadRequest("Impossible d'ajouter un rendezvous car le body est null");
        }
        Rendezvous savedRendezvous = rendezVousService.saveRendezvous(rendezvousProxy);

         if(savedRendezvous==null){
            throw new RendezvousServerError("Impossible d'ajouter un rendezvous Erreur server");
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedRendezvous.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
   @PutMapping("/rendezvous/{id}") // ici il y'a incorrence au niveau des donnees
    public ResponseEntity<Rendezvous> updateRendezvous(@PathVariable long id,@Valid  @RequestBody RendezvousProxy updateRendezvous){
        if(updateRendezvous ==null){
            throw new RendezvousBadRequest("Impossible d'ajouter un rendezvous car le body est null");
        }

        Rendezvous updatedRendezvous = rendezVousService.updateRendezvous(id,updateRendezvous);

        return ResponseEntity.ok(updatedRendezvous);

    }

    @DeleteMapping("/rendezvous/{id}")
    public String deleteRendezvous(@PathVariable  long id){
        rendezVousService.deleteRendezvous(id);
        return "Votre suppression a ete effectuer avec success";
    }
    @PutMapping("/rendezvous/paiement/{idPaiement}/{idRendezvous}")
    public ResponseEntity<String> paimentEffectuer(@PathVariable long idPaiement, @PathVariable long idRendezvous){
        try {
            boolean success = rendezVousService.effectuerPaiementSurRendezvous(idPaiement, idRendezvous);

            if (success) {
                return ResponseEntity.ok("Paiment effectuer avec success");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paiment ou le rendezvous n'existe pas");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors " +
                    "de l'association avec le rendezvous");
        }
    }
}