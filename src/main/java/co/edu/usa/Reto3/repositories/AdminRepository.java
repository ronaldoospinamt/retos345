/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.Reto3.repositories;

import co.edu.usa.Reto3.models.Admin;
import co.edu.usa.Reto3.repositories.crud.AdminCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * 
 */
@Repository
public class AdminRepository {
    
    @Autowired
    private AdminCrudRepository repo;
    
    public List<Admin> getAll() {
        return (List<Admin>) repo.findAll();
    }
    
    public Admin save(Admin admin) {
        return repo.save(admin);
    }
    
    public Optional<Admin> getById(int id) {
        return repo.findById(id);
    }
    
    public String deleteById(int id){
        repo.deleteById(id);
        return "Registro con id " + id + " ha sido eliminado"; 
    }
    
    public void delete(Admin admin){
        repo.delete(admin);
    }
}
