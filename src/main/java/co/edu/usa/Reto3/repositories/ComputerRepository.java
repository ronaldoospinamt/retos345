/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.Reto3.repositories;

import co.edu.usa.Reto3.models.Computer;
import co.edu.usa.Reto3.repositories.crud.ComputerCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * 
 */
@Repository
public class ComputerRepository {
    
    @Autowired
    private ComputerCrudRepository repo;
    
    public List<Computer> getAll() {
        return (List<Computer>) repo.findAll();
    }
    
    public Computer save(Computer computer) {
        return repo.save(computer);
    }
    
    public Optional<Computer> getById(int id) {
        return repo.findById(id);
    }
    
    public String deleteById(int id){
        repo.deleteById(id);
        return "Registro con id " + id + " ha sido eliminado"; 
    }
    
    public void delete(Computer computer){
        repo.delete(computer);
    }
}
