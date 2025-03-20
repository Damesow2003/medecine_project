package com.projetMedecine.Controller;

import com.projetMedecine.Exceptions.PrescriptionBadRequest;
import com.projetMedecine.Modele.Prescription;
import com.projetMedecine.Modele.PrescriptionProxy;
import com.projetMedecine.Service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class PrescriptionController {
    @Autowired
    PrescriptionService prescriptionService;

    @GetMapping("/prescriptions")
    public Iterable<Prescription> getPrescriptions(){
        return prescriptionService.prescriptions();
    }
    @GetMapping("/prescriptions/{id}")
    public Optional<Prescription> getPrescripiton(@PathVariable long id){
        Optional<Prescription> prescription = prescriptionService.prescription(id);

        if(prescription.isEmpty()){
            throw new PrescriptionBadRequest("La prescription correspondant a l'id "+id+" est introuvable");
        }
        return prescription;
    }

    @PostMapping("/prescriptions")
    public ResponseEntity<Prescription> savePrescription(@RequestBody PrescriptionProxy prescriptionProxy){
        if(prescriptionProxy==null){
            throw new PrescriptionBadRequest("Veuillez saisir un body valide");
        }
        Prescription savePrescription = prescriptionService.savePrescription(prescriptionProxy);

        return ResponseEntity.status(HttpStatus.CREATED).body(savePrescription);
    }

    @PutMapping("/prescriptions/{id}")
    public ResponseEntity<Prescription> updatedPrescription(@PathVariable long id,@Valid @RequestBody PrescriptionProxy prescriptionProxy){
        if(prescriptionProxy==null){
            throw new PrescriptionBadRequest("Veuillez saisir un body valide");
        }
        Prescription updatedPrescription = prescriptionService.updatePrescription(prescriptionProxy,id);

        return ResponseEntity.ok(updatedPrescription);
    }
    @DeleteMapping("/prescriptions/{id}")
    public String deletePrescription(@PathVariable long id){
        prescriptionService.deletePrescriptionById(id);
        return "Votre suppression a ete effectue avec success";
    }
}
