/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.Reto3.repositories;

import co.edu.usa.Reto3.models.Client;
import co.edu.usa.Reto3.repositories.crud.ClientCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 
 */
@Repository
public class ClientRepository {
    
    @Autowired
    private ClientCrudRepository repo;
    
    public List<Client> getAll() {
        return (List<Client>) repo.findAll();
    }
    
    public Client save(Client client) {
        return repo.save(client);
    }
    
    public Optional<Client> getById(int id) {
        return repo.findById(id);
    }
    
    public String deleteById(int id){
        repo.deleteById(id);
        return "Registro con id " + id + " ha sido eliminado"; 
    }
    
    public void delete(Client client){
        repo.delete(client);
    }
}
