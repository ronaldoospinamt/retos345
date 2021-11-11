/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.Reto3.repositories;

import co.edu.usa.Reto3.models.Message;
import co.edu.usa.Reto3.repositories.crud.MessageCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
*/
@Repository
public class MessageRepository {
    
    @Autowired
    private MessageCrudRepository repo;
    
    public List<Message> getAll() {
        return (List<Message>) repo.findAll();
    }
    
    public Message save(Message message) {
        return repo.save(message);
    }
    
    public Optional<Message> getById(int id) {
        return repo.findById(id);
    }
    
    public String deleteById(int id){
        repo.deleteById(id);
        return "Registro con id " + id + " ha sido eliminado"; 
    }
    
    public void delete(Message message){
        repo.delete(message);
    }
}
