/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.Reto3.services;

import co.edu.usa.Reto3.models.Admin;
import co.edu.usa.Reto3.repositories.AdminRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CRUD Create, Read, Update, Delete
 * @author Alexander Arango
 */
@Service
public class AdminService {

    @Autowired
    private AdminRepository repoAdmin;

    // Create
    public Admin saveAdmin(Admin admin) {
        Admin adminNew = repoAdmin.save(admin);
        return getAdminById(adminNew.getId());
    }

    //Read
    public List<Admin> getAll() {
        return repoAdmin.getAll();
    }

    //Update
    public Admin updateAdmin(Admin admin) {        
        if (admin.getId() != null){
            Optional<Admin> adminConsultada = repoAdmin.getById(admin.getId());
            if (adminConsultada.isPresent()){
                adminConsultada.get().setEmail(admin.getEmail());
                adminConsultada.get().setName(admin.getName());
                adminConsultada.get().setPassword(admin.getPassword());
                return repoAdmin.save(adminConsultada.get());
            }
        }
        return admin;
    }

    //Delete
    public boolean deleteAdmin(int id) {
        boolean del = repoAdmin.getById(id).map(admin -> {
            repoAdmin.delete(admin);
            return true;
        }).orElse(false);
        return del;
    }

    //Filtro por ID
    public Admin getAdminById(int id) {
        Optional<Admin> admin = repoAdmin.getById(id);
        return admin.orElse(new Admin());
    }
}
