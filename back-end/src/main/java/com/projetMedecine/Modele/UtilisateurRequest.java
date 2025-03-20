package com.projetMedecine.Modele;

import lombok.Data;

@Data
public class UtilisateurRequest {
    private String prenom;
    private String nom;
    private String email;
    private String password;
    private String confirmEmail;
    private String confirmPassword;
    private String telephone;
    private String adresse;
    private String role;
    private String username;
    private String dateDeNaissance;
    private String specialite; // Pour Medecin
    private String contactPreference;
}
