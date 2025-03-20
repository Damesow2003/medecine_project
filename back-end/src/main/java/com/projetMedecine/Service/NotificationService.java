package com.projetMedecine.Service;

import com.projetMedecine.Modele.Notification;
import com.projetMedecine.Modele.NotificationProxy;
import com.projetMedecine.Modele.Rendezvous;
import com.projetMedecine.Repository.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private RendezVousService rendezVousService;
    public Iterable<Notification> notifications(){
        return notificationRepository.findAll();
    }
    public Optional<Notification> notification(long id){
        return notificationRepository.findById(id);
    }

    public Notification saveNotification(NotificationProxy notificationProxy){
        Notification notification = new Notification();
        notification.setContenu(notificationProxy.getContenu());
        notification.setDateEnvoie(notificationProxy.getDateEnvoie());
        if(notificationProxy.getIdRendezvous() != 0){
            Optional<Rendezvous> rendezvous = Optional.ofNullable(rendezVousService.getRendezvousById(notificationProxy.getIdRendezvous())
                    .orElseThrow(() -> new RuntimeException("Rendez vous not found")));
            notification.setRendezvous(rendezvous.get());
        }
            return  notificationRepository.save(notification);

    }
    public Notification updateNotification(NotificationProxy notificationProxy,long id){
        Optional<Notification> existingNotification = notificationRepository.findById(id);
        Notification updateNotification = existingNotification.get();
        updateNotification.setContenu(notificationProxy.getContenu());
        updateNotification.setDateEnvoie(notificationProxy.getDateEnvoie());

        if(notificationProxy.getIdRendezvous() != null){
            Optional<Rendezvous> existingRendezvous = rendezVousService.getRendezvousById(notificationProxy.getIdRendezvous());
            updateNotification.setRendezvous(existingRendezvous.get());
        }
        return notificationRepository.save(updateNotification);
    }


    public void deleteNotification(long id) {
        notificationRepository.deleteById(id);
    }

    public List<Notification> allNotificationById(List<Long> ids){
        return notificationRepository.findAllById(ids);
    }
}
