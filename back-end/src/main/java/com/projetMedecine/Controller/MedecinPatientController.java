package com.projetMedecine.Controller;

import com.projetMedecine.Modele.RendezvousDTO;
import com.projetMedecine.Modele.TraitementDTO;
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

    @GetMapping("/{matricule}/traitements")
    public ResponseEntity<List<TraitementDTO>>  getTraitementsByMedecin(@PathVariable Long matricule,
                                                                  @RequestHeader("X-Cabinet-ID") Long cabinetId){
        List<TraitementDTO> traitements = medecinPatientService.getTraitementsByMedecin(matricule);

        if(traitements.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(traitements);
    }

    @GetMapping("/{matricule}/rendezvous")
    public ResponseEntity<List<RendezvousDTO>> getRendezvousByMedecin(@PathVariable Long matricule,
                                                                      @RequestHeader("X-Cabinet-ID") Long cabinetId){
        List<RendezvousDTO> rendezvous = medecinPatientService.getRendezvousByMedecin(matricule, cabinetId);
        if(rendezvous.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(rendezvous);
    }
}
