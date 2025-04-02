package com.projetMedecine.Service;

import com.projetMedecine.Modele.Admin;
import com.projetMedecine.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Iterable<Admin> listAdmin(){
            return adminRepository.findAll();
    }
    public Optional<Admin> getAdminById(Long id){
        return adminRepository.findById(id);
    }
    public Admin addAdmin(Admin admin){
        return adminRepository.save(admin);
    }
}
