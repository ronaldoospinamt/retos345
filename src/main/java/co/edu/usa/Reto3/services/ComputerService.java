/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.Reto3.services;

import co.edu.usa.Reto3.models.Computer;
import co.edu.usa.Reto3.repositories.ComputerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @
 */
@Service
public class ComputerService {

    @Autowired
    private ComputerRepository reposComputer;

    // CRUD Create, Read, Update, Delete
    // Create
    public Computer saveComputer(Computer computer) {
        Computer computerNew = reposComputer.save(computer);
        return getComputerById(computerNew.getId());
    }

    //Read
    public List<Computer> getAll() {
        return reposComputer.getAll();
    }

    //Update
    public Computer updateComputer(Computer computer) {        
        if (computer.getId() != null){
            Optional<Computer> computerConsultada = reposComputer.getById(computer.getId());
            if (computerConsultada.isPresent()){
                
                computerConsultada.get().setBrand(computer.getBrand());
                if(computer.getCategory() != null) {
                    computerConsultada.get().setCategory(computer.getCategory());
                }                
                computerConsultada.get().setDescription(computer.getDescription());
                computerConsultada.get().setName(computer.getName());
                computerConsultada.get().setYear(computer.getYear());
                
                return reposComputer.save(computerConsultada.get());
            }
        }
        return computer;
    }

    //Delete
    public boolean deleteComputer(int id) {
        boolean del = reposComputer.getById(id).map(computer -> {
            reposComputer.delete(computer);
            return true;
        }).orElse(false);
        return del;
    }

    //Filtro por ID
    public Computer getComputerById(int id) {
        Optional<Computer> computer = reposComputer.getById(id);
        return computer.orElse(new Computer());
    }
}
