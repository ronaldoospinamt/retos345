/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.Reto3.repositories.crud;

import co.edu.usa.Reto3.models.Message;
import org.springframework.data.repository.CrudRepository;

/**
 *
 */
public interface MessageCrudRepository extends CrudRepository<Message, Integer>{
    
}
