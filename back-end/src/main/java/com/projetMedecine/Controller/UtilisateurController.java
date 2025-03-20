package com.projetMedecine.Controller;


import com.projetMedecine.Exceptions.ImpossibleAjouterUtilisateur;
import com.projetMedecine.Exceptions.UtilisateurNotFound;
import com.projetMedecine.Modele.Medecin;
import com.projetMedecine.Modele.Patient;
import com.projetMedecine.Modele.Utilisateur;
import com.projetMedecine.Modele.UtilisateurRequest;
import com.projetMedecine.Repository.PatientRepository;
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
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth/login")
public class UtilisateurController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedecinService medecinService;
    @Autowired
    JwtEncoder jwtEncoder;

    @GetMapping("/patients")
    public Iterable<Patient> getUtilisateur(){
        return patientService.getAllPatient();
    }
    @GetMapping("/medecins")
    public Iterable<Medecin> getMedecin(){
        return medecinService.getAllMedecin();
    }

    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
    @PostMapping("/sigin")
    public Map<String,String> login(String username, String password){
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        Instant instant = Instant.now();
        String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        JwtClaimsSet claimsSet = JwtClaimsSet
                .builder()
                .issuer("self")
                .expiresAt(instant.plus(1, ChronoUnit.DAYS))
                .subject(username)
                .claim("scope",scope)
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

    @GetMapping("/forget-password")
    public ResponseEntity<Utilisateur> getAccount(@RequestParam String email){
        Utilisateur existingUser = utilisateurService.recuperCompte(email);

        if(existingUser==null){
            throw new UtilisateurNotFound("Cette utilisateur n'existe pas");
        }
        return ResponseEntity.ok(existingUser);
    }

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
