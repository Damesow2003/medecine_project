package com.projetMedecine.Service;

import com.projetMedecine.Modele.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DtoConversion {

    public CabinetMedicalDTO convertToCabinetMedicalDTO(CabinetMedical cabinet) {
        CabinetMedicalDTO cabinetMedicalDTO = new CabinetMedicalDTO();
        cabinetMedicalDTO.setId(cabinet.getIdCabinet());
        cabinetMedicalDTO.setAdresse(cabinet.getAdresse());
        cabinetMedicalDTO.setNom(cabinet.getNom());
        cabinetMedicalDTO.setImageUrl(cabinet.getImageUrl());

        // Association de la liste des salles
        cabinetMedicalDTO.setSalles(cabinet.getSalles().stream()
                .map(this::convertToSalleSimpleDTO)
                .collect(Collectors.toList()));

        //Association avec la liste des medecins
        cabinetMedicalDTO.setMedecins(cabinet.getMedecins().stream()
                .map(this::convertToMedecinSimpleDTO)
                .collect(Collectors.toList()));

        //Association avec la liste des rendezvous
        cabinetMedicalDTO.setRendezvous(cabinet.getRendezvousList().stream()
                .map(this::convertToRendezvousSimpleDTO)
                .collect(Collectors.toList()));

        return cabinetMedicalDTO;
    }
    public SalleSimpleDTO convertToSalleSimpleDTO(Salle salle) {
        SalleSimpleDTO dto = new SalleSimpleDTO();

        dto.setNomSalle(salle.getNomSalle());
        dto.setIdSalle(salle.getIdSalle());
        dto.setNumeroSalle(salle.getNumeroSalle());
        dto.setStatus(salle.getStatus());
        // ne pas inclure cabinetMedical pour eviter la reference circulaire

        return dto;
    }

    public MedecinSimpleDTO convertToMedecinSimpleDTO(Medecin medecin) {
        MedecinSimpleDTO dto = new MedecinSimpleDTO();
        dto.setMatricule(medecin.getMatricule());
        dto.setSpecialite(medecin.getSpecialite());
        dto.setNom(medecin.getNom());
        dto.setPrenom(medecin.getPrenom());

        //Ne pas inclure cabinetMedical pour eviter la reference circulaire

        return dto;

    }

    public MedecinDTO convertToMedecinDTO(Medecin medecin){
        MedecinDTO dto = new MedecinDTO();

        dto.setMatricule(medecin.getMatricule());
        dto.setSpecialite(medecin.getSpecialite());
        dto.setPrenom(medecin.getPrenom());
        dto.setNom(medecin.getNom());
        dto.setEmail(medecin.getEmail());
        dto.setTelephone(medecin.getTelephone());
        dto.setAdresse(medecin.getAdresse());
        dto.setDateDeNaissance(medecin.getDateDeNaissance());


        //Association avec cabinetMedicalDTO
        if(medecin.getCabinetMedicals() != null){
            List<CabinetSimpleDTO> cabinetSimpleDTOS = medecin.getCabinetMedicals().stream()
                    .map(cabinetMedical -> new CabinetSimpleDTO(
                            cabinetMedical.getIdCabinet(),
                            cabinetMedical.getAdresse(),
                            cabinetMedical.getNom()
                    )).collect(Collectors.toList());
            dto.setCabinets(cabinetSimpleDTOS);
        }
        //Association avec traitementDTO

        if(medecin.getTraitementList()!= null){
            List<TraitementSimpleDTO> traitementSimpleDTOS = medecin.getTraitementList().stream()
                    .map(this::convertToTraitementSimpleDTO)
                    .collect(Collectors.toList());
            dto.setTraitements(traitementSimpleDTOS);
        }

        return dto;
    }
    public TraitementSimpleDTO convertToTraitementSimpleDTO(Traitement traitement){
        TraitementSimpleDTO dto = new TraitementSimpleDTO();

        dto.setIdTraitement(traitement.getIdTraitement());
        dto.setNom(traitement.getNom());

        return dto;

    }
    public NotificationDTO convertToNotificationDTO(Notification notification) {
        NotificationDTO dto = new NotificationDTO();

        dto.setIdNotification(notification.getIdNotification());
        dto.setContenu(notification.getContenu());
        dto.setDateEnvoie(notification.getDateEnvoie());
        //Association avec RendezvousSimpleDTO
        // Vérifie si la notification est liée à un rendez-vous
        Rendezvous rendezvous = notification.getRendezvous();
        if (rendezvous != null) {
            dto.setRendezvousSimpleDTO(convertToRendezvousSimpleDTO(rendezvous));
        } else {
            dto.setRendezvousSimpleDTO(null); // ou lève une exception explicite si c'est un cas interdit
        }
        return dto;
    }

    public RendezvousSimpleDTO convertToRendezvousSimpleDTO(Rendezvous rendezvous) {
        RendezvousSimpleDTO dto = new RendezvousSimpleDTO();
        dto.setId(rendezvous.getId());
        dto.setDateHeure(rendezvous.getDateRv()+" H: "+rendezvous.getHeureRv());
        dto.setDuree(rendezvous.getDuree());
        if(rendezvous.getPatient() != null){
            dto.setPatientNomComplet(rendezvous.getPatient().getPrenom() + " " + rendezvous.getPatient().getNom());
        }

        return dto;
    }
    public PatientDTO convertToPatientDTO(Patient patient) {
        PatientDTO dto = new PatientDTO();

        dto.setIdPatient(patient.getIdPatient());
        dto.setNom(patient.getNom());
        dto.setPrenom(patient.getPrenom());
        dto.setEmail(patient.getEmail());
        dto.setDateDeNaissance(patient.getDateDeNaissance());
        dto.setAdresse(patient.getAdresse());
        dto.setRole(patient.getRole());
        //Association avec RendezvousPatientDTO
        if(patient.getRendezvous() != null) {
            dto.setRendezvous(convertToRendezvousPatientDTO(patient.getRendezvous()));
        }
        return dto;
    }

    public RendezvousPatientDTO convertToRendezvousPatientDTO(Rendezvous rendezvous) {
        RendezvousPatientDTO dto = new RendezvousPatientDTO();
        dto.setId(rendezvous.getId());
        dto.setDateHeure(rendezvous.getDateRv()+" H: "+rendezvous.getHeureRv());
        dto.setDuree(rendezvous.getDuree());
        if(rendezvous.getCabinetMedical()!=null){
            dto.setCabinetNom(rendezvous.getCabinetMedical().getNom());
        }
        return dto;

    }
    public PrescriptionDTO convertToPrescriptionDTO(Prescription prescription) {
        PrescriptionDTO dto = new PrescriptionDTO();

        dto.setIdPrescription(prescription.getIdPrescription());
        dto.setMedicament(prescription.getMedicament());
        dto.setDate(prescription.getDate());
        //Association avec RendezvousDTO
        //Verifier si prescription est liee a un rendezvous
        Rendezvous rendezvous = prescription.getRendezvous();

        if(rendezvous != null) {
            dto.setSimpleRendezvousDTO(convertToRendezVousSimpleDTO(rendezvous));
        }else{
            dto.setSimpleRendezvousDTO(null);
        }

        return dto;
    }

    public RendezvousSimpleDTO convertToRendezVousSimpleDTO(Rendezvous rendezvous) {
        RendezvousSimpleDTO dto = new RendezvousSimpleDTO();

        dto.setId(rendezvous.getId());
        dto.setDateHeure(rendezvous.getDateRv()+" H: "+rendezvous.getHeureRv());
        dto.setDuree(rendezvous.getDuree());
        if(rendezvous.getPatient()!=null) {
            dto.setPatientNomComplet(rendezvous.getPatient().getNom()+" "+rendezvous.getPatient().getPrenom());
        }

        return dto;
    }
    public SalleDTO convertToSalleDTO(Salle salle) {
        SalleDTO dto = new SalleDTO();

        dto.setNomSalle(salle.getNomSalle());
        dto.setIdSalle(salle.getIdSalle());
        dto.setNumeroSalle(salle.getNumeroSalle());
        dto.setStatus(salle.getStatus());
        // Inclure le cabinetMedical pour savoir quelle salle est associees a un cabinet
        if(salle.getCabinetMedical() != null) {
            dto.setCabinet(convertTocabinetSimpleDTO(salle.getCabinetMedical()));
        }

        //Inclure le traitementg pour savoir dans quel salle se deroule le traitement
        if(salle.getTraitements()!=null) {
            List</*TraitementSimpleDTO*/TraitementRendezvouSimpleDTO> traitementSimpleDTOList = salle.getTraitements()
                    .stream()
                    .map(this::convertToTraitementRendezvousSimpleDTO)
                    .collect(Collectors.toList());
            dto.setTraitements(traitementSimpleDTOList);
        }
        return dto;
    }

    public CabinetSimpleDTO convertTocabinetSimpleDTO(CabinetMedical cabinet){
        CabinetSimpleDTO dto = new CabinetSimpleDTO();

        dto.setIdCabinet(cabinet.getIdCabinet());
        dto.setNom(cabinet.getNom());
        dto.setAdresse(cabinet.getAdresse());

        return dto;
    }

    public TraitementDTO convertToTraitementDTO(Traitement traitement){
        TraitementDTO dto = new TraitementDTO();
        dto.setIdTraitement(traitement.getIdTraitement());
        dto.setNom(traitement.getNom());
        //Mapper le patient
       /* if(traitement.getIdPatient()!=null){
            dto.setPatient(convertToPatientSimpleDTO(traitement.getRendezvous().getPatient()));
        }*/
        if(traitement.getPatient()!=null){
            dto.setPatient(convertToPatientSimpleDTO(traitement.getPatient()));
        }
        //Mapper la salle
        if(traitement.getSalle()!=null){
            dto.setSalle(convertToSalleSimpleDTO(traitement.getSalle()));
        }
        //Mapper le medecin
        if(traitement.getMedecin()!=null){
            dto.setMedecin(convertToMedecinSimpleDTO(traitement.getMedecin()));
        }
        //Mapper le traitement

        return dto;
    }
    public PatientSimpleDTO convertToPatientSimpleDTO(Patient patient){
        PatientSimpleDTO dto = new PatientSimpleDTO();
        dto.setIdPatient(patient.getId());
        dto.setNom(patient.getNom());
        dto.setPrenom(patient.getPrenom());
        dto.setEmail(patient.getEmail());
        dto.setAdresse(patient.getAdresse());
        dto.setTelephone(patient.getTelephone());
        dto.setDateDeNaissance(patient.getDateDeNaissance());

        return dto;
    }
    public RendezvousDTO convertToRendezvousDTO(Rendezvous rendezvous){
        RendezvousDTO dto = new RendezvousDTO();

        dto.setId(rendezvous.getId());
        dto.setDateRv(rendezvous.getDateRv());
        dto.setHeureRv(rendezvous.getHeureRv());
        dto.setDuree(rendezvous.getDuree());

        // Mapper les notifications
        if(rendezvous.getNotifications() != null && !rendezvous.getNotifications().isEmpty()) {
            dto.setNotifications(rendezvous.getNotifications().stream()
                    .map(this::convertToNotificationDTO)
                    .collect(Collectors.toList()));
        }

        // Mapper les prescriptions
        if(rendezvous.getPrescriptions() != null && !rendezvous.getPrescriptions().isEmpty()) {
            dto.setPrescriptions(rendezvous.getPrescriptions().stream()
                    .map(this::convertToPrescriptionDTO)
                    .collect(Collectors.toList()));
        }

        // Mapper le patient
        if(rendezvous.getPatient() != null) {
            dto.setPatient(convertToPatientSimpleDTO(rendezvous.getPatient()));
        }
        //Mapper le cabinet
        if(rendezvous.getCabinetMedical()!= null) {
            dto.setCabinet(convertTocabinetSimpleDTO(rendezvous.getCabinetMedical()));
        }
        //Mapper le medecin(Ici il y'a pas de relation direct entre medecin et rendezvous)

        return dto;
    }

    public TraitementRendezvouSimpleDTO convertToTraitementRendezvousSimpleDTO(Traitement traitement){
        TraitementRendezvouSimpleDTO dto = new TraitementRendezvouSimpleDTO();

        dto.setIdTraitement(traitement.getIdTraitement());
        dto.setNom(traitement.getNom());

        if(traitement.getRendezvous() != null){
            dto.setRendezvous(convertToRendezvousSimpleDTO(traitement.getRendezvous()));
        }

        return dto;
    }

}
