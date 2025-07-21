package com.projetMedecine.Controller;

import com.projetMedecine.Exceptions.MedecinNotFound;
import com.projetMedecine.Exceptions.PaiementNotFound;
import com.projetMedecine.Modele.*;
import com.projetMedecine.Service.MedecinPatientRendezvousService;
import com.projetMedecine.Service.MedecinPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/medecins")
public class MedecinPatientController {
    @Autowired
    private MedecinPatientService medecinPatientService;
    @Autowired
    private MedecinPatientRendezvousService mprService;

    @GetMapping("/{matricule}/traitements")
    public List<TraitementDTO>  getTraitementsByMedecin(@PathVariable Long matricule,
                                                                  @RequestHeader("X-Cabinet-ID") Long cabinetId){
        List<TraitementDTO> traitements = medecinPatientService.getTraitementsByMedecin(matricule);

        if(traitements.isEmpty()){
            throw new RuntimeException("Aucun Traitement disponible");
        }
        return  traitements;
    }

    @GetMapping("/{matricule}/rendezvous")
    public ResponseEntity<List<RendezvousDTO>> getRendezvousByMedecin(@PathVariable Long matricule,
                                                                      /*@RequestHeader("X-Cabinet-ID") */
                                                                      @RequestParam  Long cabinetId){
        List<RendezvousDTO> rendezvous = medecinPatientService.getRendezvousByMedecin(matricule, cabinetId);
        if(rendezvous.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(rendezvous);
    }

    @GetMapping("/patients/{matricule}")
    public ResponseEntity<List<PatientSimpleDTO>> getPatientsByMedecin(@PathVariable Long matricule) {
        List<PatientSimpleDTO> patients = mprService.getPatientsByMedecin(matricule);
        if (matricule == null || patients.isEmpty()) {
            throw new PaiementNotFound("Aucun patient n'est associe a un medecin");
        }

        return ResponseEntity.ok(patients);
    }

    @GetMapping("/medecins/{idPatient}")
    public ResponseEntity<List<MedecinSimpleDTO>> getMedecinsByPatient(@PathVariable Long idPatient){

        List<MedecinSimpleDTO> medecins = mprService.getMedecinsByPatient(idPatient);
        if (medecins.isEmpty() || idPatient == null) {
            throw new MedecinNotFound("Aucun medecin n'est associees a un patient avec l'idPatient: "+ idPatient);
        }
        return ResponseEntity.ok(medecins);
    }

    @GetMapping("/rendezvous/{idPatient}")
    public ResponseEntity<PatientRendezvousDTO> getPatientRendezvous(@PathVariable Long idPatient,
                                                                     @RequestParam(required = false)String statut){
        PatientRendezvousDTO patientRendezvousDTO = mprService.getPatientRendezvous(idPatient, statut);
        if(patientRendezvousDTO == null || idPatient == null) {
            throw new RuntimeException("Aucun patient avec l'id "+ idPatient+" n'est associe a un rendezvous");
        }
        return ResponseEntity.ok(patientRendezvousDTO);
    }
}