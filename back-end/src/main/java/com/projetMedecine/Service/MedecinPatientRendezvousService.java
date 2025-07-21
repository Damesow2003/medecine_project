package com.projetMedecine.Service;

import com.projetMedecine.Modele.*;
import com.projetMedecine.Repository.MedecinRepository;
import com.projetMedecine.Repository.PatientRepository;
import com.projetMedecine.Repository.RendezVousRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedecinPatientRendezvousService {
    @Autowired
    private MedecinRepository medecinRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;
    @Autowired
    private DtoConversion dtoConversion;

    public List<PatientSimpleDTO> getPatientsByMedecin(Long matricule){
        return patientRepository.findPatientByMedecinMatricule(matricule)
                .stream()
                .map(patient -> dtoConversion.convertToPatientSimpleDTO(patient))
                .collect(Collectors.toList());
    }

    public List<MedecinSimpleDTO> getMedecinsByPatient(Long idPatient){
        return medecinRepository.findMedecinByPatientId(idPatient)
                .stream()
                .map(medecin -> dtoConversion.convertToMedecinSimpleDTO(medecin))
                .collect(Collectors.toList());
    }

    //3. Methode pour obtenir les rendezvcus d'un patient(avec filtrage par status)

    public PatientRendezvousDTO getPatientRendezvous(Long idPatient, String statut) {
        Patient patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new EntityNotFoundException("Patient non trouvé"));

        List<Rendezvous> rendezvousList = switch (statut != null ? statut.toUpperCase() : "") {
            case "PASSÉ" -> rendezVousRepository.findPastRendezvousByPatient(idPatient);
            case "EN_COURS" -> rendezVousRepository.findCurrentRendezvousByPatient(idPatient);
            default -> rendezVousRepository.findByPatientId(idPatient);  // Utilise la nouvelle méthode
        };

        PatientRendezvousDTO dto = new PatientRendezvousDTO();
        dto.setPatient(dtoConversion.convertToPatientSimpleDTO(patient));

        dto.setRendezvous(rendezvousList.stream()
                .map(this::convertToRendezvousDto)
                .collect(Collectors.toList()));

        return dto;
    }

    private RendezvousPatientCabinetDTO convertToRendezvousDto(Rendezvous r) {
        RendezvousPatientCabinetDTO dto = new RendezvousPatientCabinetDTO();
        dto.setId(r.getId());
        dto.setDateHeure(r.getDateRv() + " H: " + r.getHeureRv());
        dto.setDuree(r.getDuree());
        dto.setStatut(calculateRendezvousStatus(r));

        if (r.getCabinetMedical() != null) {
            dto.setCabinet(new CabinetSimpleDTO(
                    r.getCabinetMedical().getIdCabinet(),
                    r.getCabinetMedical().getAdresse(),
                    r.getCabinetMedical().getNom()
            ));
        }

        if (r.getTraitement() != null && r.getTraitement().getMedecin() != null) {
            Medecin m = r.getTraitement().getMedecin();
            dto.setMedecin(new MedecinSimpleDTO(
                    m.getMatricule(),
                    m.getSpecialite(),
                    m.getNom(),
                    m.getPrenom()
            ));
        }

        return dto;
    }
    private String calculateRendezvousStatus(Rendezvous rendezvous) {
        LocalDate rvDate = LocalDate.parse(rendezvous.getDateRv());
        LocalTime rvTime = LocalTime.parse(rendezvous.getHeureRv());
        LocalDateTime rvDateTime = LocalDateTime.of(rvDate, rvTime);

        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(rvDateTime)) {
            return "A_VENIR";
        } else if (now.isAfter(rvDateTime.plusMinutes(rendezvous.getDuree()))) {
            return "PASSE";
        } else {
            return "EN_COURS";
        }
    }
}
