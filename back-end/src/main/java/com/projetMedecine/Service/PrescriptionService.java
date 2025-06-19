package com.projetMedecine.Service;


import com.projetMedecine.Modele.*;
import com.projetMedecine.Repository.PrescriptionRepository;
import com.projetMedecine.Repository.RendezVousRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;
    @Autowired
    private DtoConversion dtoConversion;
    /*public Iterable<Prescription> prescriptions(){

        return prescriptionRepository.findAll();
    }*/
    public List<PrescriptionDTO> prescriptions() {
        return StreamSupport.stream(prescriptionRepository.findAll().spliterator(),false)
                .map(/*this::convertToPrescriptionDTO*/ prescription -> this.dtoConversion.convertToPrescriptionDTO(prescription))
                .collect(Collectors.toList());
    }


    public Optional<PrescriptionDTO> prescription(long id){
        return prescriptionRepository.findById(id).map(prescription -> this.dtoConversion.convertToPrescriptionDTO(prescription));
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
