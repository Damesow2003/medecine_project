package com.projetMedecine.Service;


import com.projetMedecine.Modele.Prescription;
import com.projetMedecine.Modele.PrescriptionProxy;
import com.projetMedecine.Modele.Rendezvous;
import com.projetMedecine.Repository.PrescriptionRepository;
import com.projetMedecine.Repository.RendezVousRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;
    public Iterable<Prescription> prescriptions(){

        return prescriptionRepository.findAll();
    }
    public Optional<Prescription> prescription(long id){
        return prescriptionRepository.findById(id);
    }

    public void deletePrescriptionById(long id){
        prescriptionRepository.deleteById(id);
    }

    public Prescription savePrescription(PrescriptionProxy prescriptionProxy){
        Prescription newPrescription = new Prescription();
        newPrescription.setMedicament(prescriptionProxy.getMedicament());
        newPrescription.setDate(prescriptionProxy.getDate());

        if(prescriptionProxy.getIdRendezvous()!=null){
            Optional<Rendezvous> existingRendezvous = Optional.ofNullable(rendezVousRepository.findById(prescriptionProxy.getIdRendezvous())
                    .orElseThrow(() -> new RuntimeException("Rendezvous not found")));
            newPrescription.setRendezvous(existingRendezvous.get());
        }
        return prescriptionRepository.save(newPrescription);
    }
    public Prescription updatePrescription(PrescriptionProxy prescriptionProxy,long id){
         Optional<Prescription> existingPrescription = prescriptionRepository.findById(id);

         Prescription updatePrescription = existingPrescription.get();
         updatePrescription.setMedicament(prescriptionProxy.getMedicament());
         updatePrescription.setDate(prescriptionProxy.getDate());
        Optional<Rendezvous> existingRendezvous = Optional.ofNullable(rendezVousRepository.findById(prescriptionProxy.getIdRendezvous())
                .orElseThrow(() -> new RuntimeException("Rendezvous not found")));
        updatePrescription.setRendezvous(existingRendezvous.get());

        return prescriptionRepository.save(updatePrescription);
    }
}
