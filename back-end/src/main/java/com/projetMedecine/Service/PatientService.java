package com.projetMedecine.Service;

import com.projetMedecine.Modele.*;
import com.projetMedecine.Repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DtoConversion dtoConversion;

   /* public List<Patient> findPatientByTraitement(long id){
        List<Patient> patientTraitement= patientRepository.findPatientByTraitement(id);

        if(patientTraitement.isEmpty()){
            throw new TraitementBadRequest("il y'a aucun patient associer a un traitement ");
        }
        return patientTraitement;
    }*/
    public List<PatientDTO> getAllPatients(){
        return StreamSupport.stream(patientRepository.findAll().spliterator(),false)
                .map(/*this::convertToPatientDTO*/ salle-> this.dtoConversion.convertToPatientDTO(salle))
                .collect(Collectors.toList());
    }



    public Optional<PatientDTO> getPatientById(Long idPatient) {
        return patientRepository.findById(idPatient).map(salle-> this.dtoConversion.convertToPatientDTO(salle));
    }

    public Patient savedPatient(Patient patient){
        return patientRepository.save(patient);
    }
}
