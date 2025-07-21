package com.projetMedecine.Controller;


import com.projetMedecine.Exceptions.MedecinNotFound;
import com.projetMedecine.Exceptions.UtilisateurNotFound;
import com.projetMedecine.Modele.*;
import com.projetMedecine.Repository.UtilisateurRepository;
import com.projetMedecine.Service.AdminService;
import com.projetMedecine.Service.MedecinService;
import com.projetMedecine.Service.PatientService;
import com.projetMedecine.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth/login")
public class UtilisateurController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    /*private UtilisateurService utilisateurService;*/
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PatientService patientService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private MedecinService medecinService;
    @Autowired
    JwtEncoder jwtEncoder;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientDTO>> getUtilisateur(){
        List<PatientDTO> patientInfoDTOS = patientService.getAllPatients();
        if(patientInfoDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(patientInfoDTOS);
    }
    @GetMapping("/patients/{id}")
    public ResponseEntity<Optional<PatientDTO>> getUtilisateurById(@PathVariable Long id){
        Optional<PatientDTO> patientInfoDTO = patientService.getPatientById(id);
        if(patientInfoDTO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(patientInfoDTO);
    }

    @GetMapping("/medecins")
    public ResponseEntity<List<MedecinDTO>> getMedecins(){
        List<MedecinDTO> medecinDTOList = medecinService.getAllMedecin();
        if(medecinDTOList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(medecinDTOList);
    }

    @GetMapping("/medecins/{id}")
    public ResponseEntity<MedecinDTO> getMedecinById(@PathVariable Long id){
        Optional<MedecinDTO> medecinDTO = medecinService.getMedecinByMatricule(id);
         MedecinDTO existingMedecinDTO = medecinDTO.get();

        if(existingMedecinDTO==null){
            throw new MedecinNotFound("Aucun medecin associes a cette id: "+id+" est introuvable");
        }

        return ResponseEntity.ok(existingMedecinDTO);
    }
    @GetMapping("/admins")
    public Iterable<Admin> getAdmins(){return adminService.listAdmin();}
    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
    @PostMapping("/sigin")
    public Map<String,String> login(String username, String password){
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        Instant instant = Instant.now();
        String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        //Recuperer l'utilisateur depuis l'objet principal
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(userDetails.getUsername());
        Utilisateur utilisateur = utilisateurRepository.findByUsername(userDetails.getUsername());

        if(utilisateur.getRole()=="medecin"&& utilisateur.getId()!=null){
           return null;
        }

        if(utilisateur==null){
            throw new RuntimeException("Utilisateur non trouvee");
        }

        System.out.println("ID: "+ utilisateur.getId());



        JwtClaimsSet claimsSet = JwtClaimsSet
                .builder()
                .issuer("self")
                .expiresAt(instant.plus(1, ChronoUnit.DAYS))
                .subject(username)
                .claim("scope",scope)
                .claim("id", utilisateur.getId())
                .build();
        JwtEncoderParameters jwtEncoderParameters =
                JwtEncoderParameters.from(
                        JwsHeader.with(MacAlgorithm.HS256).build(),
                        claimsSet
                );
        String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();

        return Map.of("access-token",jwt);
    }
   @PostMapping("/signup")
    public ResponseEntity<?> saveUser(@RequestBody UtilisateurRequest userRequest) {
        Utilisateur user;

        // Vérification du rôle
        if ("patient".equalsIgnoreCase(userRequest.getRole())) {
            user = new Patient();
            ((Patient) user).setDateDeNaissance(userRequest.getDateDeNaissance());
            ((Patient) user).setConfirmEmail(userRequest.getConfirmEmail());
            ((Patient) user).setConfirmPassword(userRequest.getConfirmPassword());
            ((Patient) user).setContactPreference(userRequest.getContactPreference());
        } else if ("medecin".equalsIgnoreCase(userRequest.getRole())) {
            user = new Medecin();
            ((Medecin) user).setSpecialite(userRequest.getSpecialite());
        } else {
            return ResponseEntity.badRequest().body("Type d'utilisateur non supporté.");
        }

        // Initialisation des champs communs
        user.setPrenom(userRequest.getPrenom());
        user.setNom(userRequest.getNom());
        user.setEmail(userRequest.getEmail());

        // Encodage du mot de passe
        user.setPassword(passwordEncoder().encode(userRequest.getPassword()));
        user.setConfirmPassword(passwordEncoder().encode(userRequest.getConfirmPassword()));

        user.setTelephone(userRequest.getTelephone());
        user.setAdresse(userRequest.getAdresse());
        user.setRole(userRequest.getRole());
        user.setUsername(userRequest.getUsername());

        // Enregistrement en fonction du type d'utilisateur
        Utilisateur savedUser;
        if (user instanceof Patient) {
            savedUser = patientService.savedPatient((Patient) user);
        } else {
            savedUser = medecinService.savedMedecin((Medecin) user);
        }

        if (savedUser == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Impossible d'ajouter l'utilisateur. Veuillez réessayer plus tard.");
        }

        // Construction de l'URI de réponse
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

  /*  @GetMapping("/forget-password")
    public ResponseEntity<Utilisateur> getAccount(@RequestParam String email){
        Utilisateur existingUser = utilisateurService.recuperCompte(email);

        if(existingUser==null){
            throw new UtilisateurNotFound("Cette utilisateur n'existe pas");
        }
        return ResponseEntity.ok(existingUser);
    }*/

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
