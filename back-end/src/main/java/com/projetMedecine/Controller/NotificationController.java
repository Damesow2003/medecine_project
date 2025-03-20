package com.projetMedecine.Controller;

import com.projetMedecine.Exceptions.NotificationNotFound;
import com.projetMedecine.Exceptions.PrescriptionBadRequest;
import com.projetMedecine.Modele.Notification;
import com.projetMedecine.Modele.NotificationProxy;
import com.projetMedecine.Modele.Rendezvous;
import com.projetMedecine.Service.NotificationService;
import com.projetMedecine.Service.RendezVousService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private RendezVousService rendezVousService;

    @GetMapping("/notifications")
    public Iterable<Notification> getNotifications(){
        return notificationService.notifications();
    }

    @GetMapping("/notifications/{id}")
    public ResponseEntity<Optional<Notification>> getNotification(@PathVariable long id){
        Optional<Notification> existingNotification = notificationService.notification(id);
        if(!existingNotification.isPresent()){
            throw new NotificationNotFound("la notification avec l'id "+id+" est introuvable");
        }
        return ResponseEntity.ok(existingNotification);
    }
    @PostMapping("/notifications")
    public ResponseEntity<Notification> savedNotification(@Valid @RequestBody NotificationProxy notificationProxy){
        if(notificationProxy==null){
            throw new PrescriptionBadRequest("Veuillez saisir un body valide");
        }
        Notification notification = notificationService.saveNotification(notificationProxy);
        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }
    @PutMapping("/notifications/{id}")
    public ResponseEntity<Notification> updatedNotification(@PathVariable long id,@Valid @RequestBody NotificationProxy notificationProxy){
        if(notificationProxy==null){
            throw new PrescriptionBadRequest("Veuillez saisir un body valide");
        }
        Notification updatedNotification =  notificationService.updateNotification(notificationProxy,id);
        return ResponseEntity.ok(updatedNotification);
    }

    @DeleteMapping("/notifications/{id}")
    public String deleteMapping(@PathVariable long id){
        notificationService.deleteNotification(id);
        return "La suppression a ete effectue avec success";
    }
}
