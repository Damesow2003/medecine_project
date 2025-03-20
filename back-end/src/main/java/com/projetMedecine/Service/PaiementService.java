package com.projetMedecine.Service;

import com.projetMedecine.Modele.Paiement;
import com.projetMedecine.Modele.PaiementProxy;
import com.projetMedecine.Repository.PaiementRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class PaiementService {
    @Autowired
    private PaiementRepository paiementRepository;
    @Autowired
    private EntityManager entityManager;

    public Iterable<Paiement> paiements(){
        return paiementRepository.findAll();
    }

    public Optional<Paiement> paiement(long id){
        return paiementRepository.findById(id);
    }

    public Paiement savePaiement(PaiementProxy paiementProxy){

        Paiement newPaiement = new Paiement();
        newPaiement.setModeDePaiement(paiementProxy.getModeDePaiement());
        newPaiement.setMontant(paiementProxy.getMontant());

        return paiementRepository.save(newPaiement);
    }
    public Paiement updatePaiement(PaiementProxy paiementProxy, long id){
        Optional<Paiement> existingPaiement = paiementRepository.findById(id);
        Paiement paiement = existingPaiement.get();

        paiement.setModeDePaiement(paiementProxy.getModeDePaiement());
        paiement.setMontant(paiementProxy.getMontant());

        return paiementRepository.save(paiement);
    }
    public Paiement effectuerPaiement(long idPaiement) throws Exception {
        int updateRows = paiementRepository.effectuerUnPaiement(idPaiement);

       if(updateRows > 0){
           return entityManager.find(Paiement.class, idPaiement);
       }else{
            throw new RuntimeException("Le paiement avec l'id "+ idPaiement + "n'existe pas");
       }
    }

    public void deletePaiementById(long id){
        paiementRepository.deleteById(id);
    }
}