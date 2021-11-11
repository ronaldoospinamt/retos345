/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.Reto3.services;

import co.edu.usa.Reto3.models.Message;
import co.edu.usa.Reto3.repositories.MessageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.usa.Reto3.repositories.crud.MessageCrudRepository;
import java.util.Optional;

/**
 *
 * 
 */
@Service
public class MessageService {

    @Autowired
    private MessageRepository reposMessage;

    // CRUD Create, Read, Update, Delete
    // Create
    public Message saveMessage(Message message) {
        Message messageNew = reposMessage.save(message);
        return getMessageById(messageNew.getIdMessage());
    }

    //Read
    public List<Message> getAll() {
        return reposMessage.getAll();
    }

    //Update
    public Message updateMessage(Message message) {        
        if (message.getIdMessage() != null){
            Optional<Message> messageConsultada = reposMessage.getById(message.getIdMessage());
            if (messageConsultada.isPresent()){
                
                messageConsultada.get().setMessageText(message.getMessageText());
                messageConsultada.get().setClient(message.getClient());
                messageConsultada.get().setComputer(message.getComputer());
                
                return reposMessage.save(messageConsultada.get());
            }
        }
        return message;
    }

    //Delete
    public boolean deleteMessage(int id) {
        boolean del = reposMessage.getById(id).map(message -> {
            reposMessage.delete(message);
            return true;
        }).orElse(false);
        return del;
    }

    //Filtro por ID
    public Message getMessageById(int id) {
        Optional<Message> message = reposMessage.getById(id);
        return message.orElse(new Message());
    }
}
