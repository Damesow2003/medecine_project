package com.projetMedecine.Service;

import com.projetMedecine.Modele.*;
import com.projetMedecine.Repository.NotificationRepository;
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
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Autowired
    private DtoConversion dtoConversion;

    public List<NotificationDTO> notifications(){
        return StreamSupport.stream(this.notificationRepository.findAll().spliterator(),false)
                .map(/*this::convertToNotificationDTO*/ notification -> this.dtoConversion.convertToNotificationDTO(notification))
                .collect(Collectors.toList());
    }
    public Optional<NotificationDTO> notification(long id){
        return notificationRepository.findById(id).map(notification -> this.dtoConversion.convertToNotificationDTO(notification));
    }




    public Notification saveNotification(NotificationProxy notificationProxy){
        Notification notification = new Notification();
        notification.setContenu(notificationProxy.getContenu());
        notification.setDateEnvoie(notificationProxy.getDateEnvoie());
        if(notificationProxy.getIdRendezvous() != 0){
            Optional<Rendezvous> rendezvous = Optional.ofNullable(rendezVousRepository.findById(notificationProxy.getIdRendezvous())
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
            Optional<Rendezvous> existingRendezvous = rendezVousRepository.findById(notificationProxy.getIdRendezvous());
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
