package com.projetMedecine.Configuration;

import com.projetMedecine.Modele.Utilisateur;
import com.projetMedecine.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomConfig implements UserDetailsService {
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur dbUser = utilisateurRepository.findByUsername(username);

        //return new User(dbUser.getEmail(), dbUser.getPassword(),getGrantedAuthority(dbUser.getRole()));
        return new User(dbUser.getUsername(), dbUser.getPassword(), getGrantedAuthority(dbUser.getRole()));
    }

    private List<GrantedAuthority> getGrantedAuthority(String role){
        List<GrantedAuthority> autorities = new ArrayList<GrantedAuthority>();
        autorities.add(new SimpleGrantedAuthority("ROLE_"+role));

        return autorities;
    }

 }
