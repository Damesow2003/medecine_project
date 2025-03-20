package com.projetMedecine.Controller;

import com.projetMedecine.Exceptions.TraitementBadRequest;
import com.projetMedecine.Modele.Traitement;
import com.projetMedecine.Modele.TraitementProxy;
import com.projetMedecine.Service.PatientService;
import com.projetMedecine.Service.TraitementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class TraitementController {
    @Autowired
    private TraitementService traitementService;
    @Autowired
    private PatientService patientService;
    @GetMapping("/traitements")
    public Iterable<Traitement> getTraitements(){
        return traitementService.getTraitements();
    }

    @GetMapping("/traitements/{id}")
    public Optional<Traitement> getTraitement(@PathVariable long id){
        return traitementService.getTraitement(id);
    }

    @GetMapping("/traitements-patients/{id}")
    public List<Traitement> traitementsPatients(@PathVariable long id){
        List<Traitement> traitements = traitementService.findTraitementByidPatient(id);
        return  traitements;
    }

    @PostMapping("/traitements")
    public ResponseEntity<Traitement> saveTraitement(@Valid @RequestBody TraitementProxy traitementProxy){
        if(traitementProxy == null){
            throw new TraitementBadRequest("Le body de traitement ne doit pas null ou manquer quelque element");
        }
        Traitement savedTraitement = traitementService.saveTraitement(traitementProxy);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTraitement);
    }
    @PutMapping("/traitements/{id}")
    public ResponseEntity<Traitement> updateTraitement(@Valid @RequestBody TraitementProxy traitementProxy,@PathVariable long id){

        Traitement updatedTraitement = traitementService.updateTraitement(traitementProxy,id);

        return ResponseEntity.ok(updatedTraitement);
    }

    @DeleteMapping("/traitements/{id}")
    public String deleteTraitement(@PathVariable long id){
        traitementService.deleteTraitement(id);
        return "La suppression a ete effectue avec succes";
    }
}
