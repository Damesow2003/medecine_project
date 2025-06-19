package com.projetMedecine.Controller;

import com.projetMedecine.Exceptions.SalleBadRequest;
import com.projetMedecine.Modele.Salle;
import com.projetMedecine.Modele.SalleDTO;
import com.projetMedecine.Modele.SalleProxy;
import com.projetMedecine.Service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class SalleController {
    @Autowired
    private SalleService salleService;

    @GetMapping("/salles")
    public ResponseEntity<List<SalleDTO>> getSalles() {
        List<SalleDTO> salles = salleService.salles();

        if(salles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(salles);
    }

    @GetMapping("/salles/{id}")
    public ResponseEntity<Optional<SalleDTO>> getSalle(@PathVariable long id) {
       Optional<SalleDTO> salleDTOOptional = salleService.salle(id);

       if(salleDTOOptional.isEmpty()) {
           throw new SalleBadRequest("Salle non trouv<UNK>");
       }
       return ResponseEntity.ok(salleDTOOptional);
    }

    @PostMapping("/salles")
    public ResponseEntity<Salle> savedSalle(@RequestBody SalleProxy salleProxy) {
        if (salleProxy == null) {
            throw new SalleBadRequest("le body de salle contient une erreur");
        }
        Salle saveSalle = salleService.saveSalle(salleProxy);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveSalle);
    }

    @PutMapping("/salles/{id}")
    public ResponseEntity<Salle> updateSalle(@PathVariable long id, @RequestBody SalleProxy salleProxy) {
        if(salleProxy==null){
            throw new SalleBadRequest("Verifiez le body de la salle");
        }
        Salle updateSalle = salleService.updateSalle(salleProxy,id);
        return ResponseEntity.ok(updateSalle);
    }

    @DeleteMapping("/salles/{id}")
    public String deleteSalle(@PathVariable long id) {
        salleService.deleteSalle(id);
        return "La Suppression de la salle a ete effectuer avec success";
    }
}
