package com.projetMedecine.Service;

import com.projetMedecine.Exceptions.TraitementBadRequest;
import com.projetMedecine.Modele.*;
import com.projetMedecine.Repository.RendezVousRepository;
import com.projetMedecine.Repository.SalleRepository;
import com.projetMedecine.Repository.TraitementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class TraitementService {
    @Autowired
    private TraitementRepository traitementRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;
    @Autowired
    private DtoConversion dtoConversion;


    public List<TraitementDTO> getTraitements(){
        return StreamSupport.stream(this.traitementRepository.findAll().spliterator(),false)
                .map(/*this::convertToTraitementDTO*/traitement -> this.dtoConversion.convertToTraitementDTO(traitement))
                .collect(Collectors.toList());
    }

    public Optional<TraitementDTO> getTraitement(long id){
        return  traitementRepository.findById(id)
                .map(traitement -> this.dtoConversion.convertToTraitementDTO(traitement));
    }



    public Traitement saveTraitement(TraitementProxy traitementProxy){
        Traitement newTraitement = new Traitement();
        newTraitement.setNom(traitementProxy.getNom());

        if(traitementProxy.getIdSalle()!=0){
            Optional<Salle> existingSalle = Optional.ofNullable(salleRepository.findById(traitementProxy.getIdSalle())
                    .orElseThrow(() -> new TraitementBadRequest("La salle avec l'id " + traitementProxy.getIdSalle() + " est introuvable")));
            newTraitement.setSalle(existingSalle.get());
        }

        if(traitementProxy.getIdRendezvous()!=0){
            Optional<Rendezvous> existingRendezvous = Optional.ofNullable(rendezVousRepository.findById(traitementProxy.getIdRendezvous())
                    .orElseThrow(() -> new TraitementBadRequest("la rendezvous avec l'id " + traitementProxy.getIdRendezvous() + " est introuvable")));
            newTraitement.setRendezvous(existingRendezvous.get());
        }

       /* if(traitementProxy.getMatriculeMedecin() != 0){
            Optional<Medecin> existingMedecin = Optional.ofNullable(medecinRepository.findById(traitementProxy.getMatriculeMedecin())
                    .orElseThrow(() -> new TraitementBadRequest("le medecin avec la matricule " + traitementProxy.getMatriculeMedecin() + "est introuvable")));
            newTraitement.setMedecin(existingMedecin.get());
        }*/

        return traitementRepository.save(newTraitement);
    }
    public Traitement updateTraitement(TraitementProxy traitementProxy,long id){
        Optional<Traitement> existingTraitement = traitementRepository.findById(id);
        Traitement updatedTraitement = existingTraitement.get();

        updatedTraitement.setNom(traitementProxy.getNom());


        if(traitementProxy.getIdSalle()!=0){
            Optional<Salle> existingSalle = salleRepository.findById(traitementProxy.getIdSalle());
            updatedTraitement.setSalle(existingSalle.get());
        }

        if(traitementProxy.getIdRendezvous()!=0){
            Optional<Rendezvous> existingRendezvous = rendezVousRepository.findById(traitementProxy.getIdRendezvous());
            updatedTraitement.setRendezvous(existingRendezvous.get());
        }

       /* if(traitementProxy.getMatriculeMedecin()!=0){
            Optional<Medecin> existingMedecin = medecinRepository.findById(traitementProxy.getMatriculeMedecin());
            updatedTraitement.setMedecin(existingMedecin.get());
        }*/
        return traitementRepository.save(updatedTraitement);
    }


    public List<Traitement> findTraitementByidPatient(long id){
        List<Traitement> traitementPatient = traitementRepository.findTraitementByIdPatient(id);

        if(traitementPatient.isEmpty()){
            throw new TraitementBadRequest("il y'a aucun traitement associer a cette id "+id);
        }
        return traitementPatient;
    }

    public void deleteTraitement(long id){
        traitementRepository.deleteById(id);
    }
}
