package com.projetMedecine.Controller;


import com.projetMedecine.Exceptions.CabinetMedicalBadRequest;
import com.projetMedecine.Modele.CabinetMedical;
import com.projetMedecine.Modele.CabinetMedicalProxy;
import com.projetMedecine.Modele.CabinetRendezvous;
import com.projetMedecine.Service.CabinetMedicalService;
import com.projetMedecine.Service.RendezVousService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class CabinetMedicalController {
    @Autowired
    private CabinetMedicalService cabinetMedicalService;
    @Autowired
    private RendezVousService rendezVousService;

    @GetMapping("/cabinets")
    public Iterable<CabinetMedical> getCabinetMedical() {
        return cabinetMedicalService.getCabinetMedicals();
    }

    @GetMapping("/cabinets/{id}")
    public Optional<CabinetMedical> getCabinetMedicalById(@PathVariable long id) {
        return cabinetMedicalService.getCabinetMedical(id);
    }

    @PostMapping("/cabinets")
    public ResponseEntity<CabinetMedical> saveCabinetMedical( @Valid @RequestBody CabinetMedicalProxy cabinetMedicalProxy) {
        if (cabinetMedicalProxy == null) {
            throw new CabinetMedicalBadRequest("Verifiez le body de la cabinetMedical");
        }
        CabinetMedical saveCabinetMedical = cabinetMedicalService.saveCabinetMedical(cabinetMedicalProxy);

        return ResponseEntity.status(HttpStatus.CREATED).body(saveCabinetMedical);
    }

    @PutMapping("/cabinets/{id}")
    public ResponseEntity<CabinetMedical> updateCabinetMedical(@PathVariable long id,@Valid @RequestBody CabinetMedicalProxy cabinetMedical){
        Optional<CabinetMedical> existingCabinet = cabinetMedicalService.getCabinetMedical(id);
        if(existingCabinet.isEmpty()){
            throw new RuntimeException("Veuillez saisir un body valide");
        }
        CabinetMedical updatedCabinetMedical = cabinetMedicalService.updateCabinetMedical(id,cabinetMedical);

        return ResponseEntity.ok(updatedCabinetMedical);
    }
    @DeleteMapping("/cabinets/{id}")
    public String deleteCabinetMedical(@PathVariable long id){
        cabinetMedicalService.deleteCabinetMedical(id);
        return "Le cabinet a ete supprimer avec success";
    }
    @GetMapping("/cabinets/{cabinetId}/rendezvous")/*
    public List<CabinetRendezvous> getRendezvousCabinet(@PathVariable long cabinetId){
        return rendezVousService.getCabinetRendezvous(cabinetId);
    }*/
    public Map<String, Object> getRendezvousCabinet(@PathVariable long cabinetId){
        return  rendezVousService.getCabinetRendezvous(cabinetId);
    }
}