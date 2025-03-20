package com.projetMedecine.Service;

import com.projetMedecine.Exceptions.PaiementNotFound;
import com.projetMedecine.Exceptions.RendezvousBadRequest;
import com.projetMedecine.Modele.*;
import com.projetMedecine.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RendezVousService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private PaiementRepository paiementRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;
    @Autowired
    private CabinetMedicalRepository cabinetMedicalRepository;

    public Iterable<Rendezvous> listRendezvous(){
        return rendezVousRepository.findAll();
    }
    public Optional<Rendezvous> getRendezvousById(long id){
        return rendezVousRepository.findById(id);
    }
    public Rendezvous saveRendezvous(RendezvousProxy rendezvousProxy){
        Rendezvous newRendezvous = new Rendezvous();

        newRendezvous.setDateRv(rendezvousProxy.getDateRv());
        newRendezvous.setHeureRv(rendezvousProxy.getHeureRv());
        newRendezvous.setDuree(rendezvousProxy.getDuree());

        //Association avec Notification
        /*List<Notification> notificationList = notificationRepository.findAllById(rendezvousProxy.getIdNotification());
        newRendezvous.setNotifications(notificationList);*/

        //Association avec Prescription
        /*List<Prescription> prescriptionList = prescriptionRepository.findAllById(rendezvousProxy.getIdPrescription());
        newRendezvous.setPrescriptions(prescriptionList);*/

        //Association avec Paiement
        /*Optional<Paiement> existingPaiement = Optional.ofNullable(paiementRepository.findById(rendezvousProxy.getIdPaiement())
                .orElseThrow(() -> new PaiementNotFound("Paiement non trouve")));
        newRendezvous.setPaiement(existingPaiement.get());*/
        //Association avec Cabinet
        Optional<CabinetMedical> existingCabinet = Optional.ofNullable(cabinetMedicalRepository.findById(rendezvousProxy.getIdCabinet())
                .orElseThrow(() -> new RuntimeException("Cabinet medecial non trouve")));
        newRendezvous.setCabinetMedical(existingCabinet.get());
        return rendezVousRepository.save(newRendezvous);
    }
    public Rendezvous updateRendezvous(long id, RendezvousProxy rendezvousProxy){
        Optional<Rendezvous> existingRendezvous = rendezVousRepository.findById(id);
        if(existingRendezvous.isEmpty()){
            throw new RendezvousBadRequest("Veuillez saisir un body non null (remplissez les infos que vous souhaitez mettre a jour)");
        }
        Rendezvous updatedRendezvous = existingRendezvous.get();
        updatedRendezvous.setDateRv(rendezvousProxy.getDateRv());
        updatedRendezvous.setHeureRv(rendezvousProxy.getHeureRv());
        updatedRendezvous.setDuree(rendezvousProxy.getDuree());
/*
        Optional<Paiement> existingPaiement = paiementRepository.findById(rendezvousProxy.getIdPaiement());
        if(existingRendezvous.isPresent()){
            updatedRendezvous.setPaiement(existingPaiement.get());
        }*/
        Optional<CabinetMedical> existingCabinet = cabinetMedicalRepository.findById(rendezvousProxy.getIdCabinet());
        if(existingCabinet.isPresent()){
            updatedRendezvous.setCabinetMedical(existingCabinet.get());
        }
        return updatedRendezvous;
    }
    public void deleteRendezvous(long id){
        rendezVousRepository.deleteById(id);
    }

  /*  public List<CabinetRendezvous> getCabinetRendezvous(long cabinetId){
        return  rendezVousRepository.findRendezvousByCabinetMedical_IdCabinet(cabinetId)
                .stream()
                .map(rendezvous -> new CabinetRendezvous(
                        rendezvous.getCabinetMedical().getAdresse(),
                        rendezvous.getCabinetMedical().getNom(),
                        new RendezvousProxy(
                                rendezvous.getDateRv(),
                                rendezvous.getHeureRv(),
                                rendezvous.getDuree(),
                               // rendezvous.getPaiement().getId(),
                                rendezvous.getCabinetMedical().getIdCabinet()
                        )
                )).collect(Collectors.toList());
    }*/

    //retourner un objet avec la liste des rendezvous
  public Map<String, Object> getCabinetRendezvous(long cabinetId) {
      List<RendezvousProxy> rendezvousProxies = rendezVousRepository.findRendezvousByCabinetMedical_IdCabinet(cabinetId)
              .stream()
              .map(rendezvous -> new RendezvousProxy(
                      rendezvous.getId(),
                      rendezvous.getDateRv(),
                      rendezvous.getHeureRv(),
                      rendezvous.getDuree(),
                      rendezvous.getCabinetMedical().getIdCabinet()
              ))
              .collect(Collectors.toList());

      // Récupération des informations du cabinet
      String adresse = rendezvousProxies.isEmpty() ? "" : rendezVousRepository.findById(rendezvousProxies.get(0).getIdCabinet())
              .map(r -> r.getCabinetMedical().getAdresse())
              .orElse("");

      String nomCabinet = rendezvousProxies.isEmpty() ? "" : rendezVousRepository.findById(rendezvousProxies.get(0).getIdCabinet())
              .map(r -> r.getCabinetMedical().getNom())
              .orElse("");

      // Création de la réponse JSON sous forme d'objet
      Map<String, Object> result = new HashMap<>();
      result.put("adresse", adresse);
      result.put("nom", nomCabinet);
      result.put("rendezvousList", rendezvousProxies);


      return result;
  }

// ici, la methode permet d'effectuer un paiement sur un rendezvous en mettant a jour la cle
// etrangere de idPaiement qui se trouve sur Rendezvous
  public boolean effectuerPaiementSurRendezvous(Long idPaiement, Long idRendezvous){

      int idRendezvousUpdated = rendezVousRepository.paiementEffectuerSurRendezvous(idPaiement,idRendezvous);

     return idRendezvousUpdated > 0; // c'est equivaut a true
  }


}