package com.projetMedecine.Service;

import com.projetMedecine.Exceptions.TraitementBadRequest;
import com.projetMedecine.Modele.Patient;
import com.projetMedecine.Modele.Traitement;
import com.projetMedecine.Repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

   /* public List<Patient> findPatientByTraitement(long id){
        List<Patient> patientTraitement= patientRepository.findPatientByTraitement(id);

        if(patientTraitement.isEmpty()){
            throw new TraitementBadRequest("il y'a aucun patient associer a un traitement ");
        }
        return patientTraitement;
    }*/

    public Iterable<Patient> getAllPatient(){
        return patientRepository.findAll();
    }

    public Patient savedPatient(Patient patient){
        return patientRepository.save(patient);
    }
}
