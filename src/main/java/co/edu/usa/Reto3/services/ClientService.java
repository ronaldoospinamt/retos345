/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.Reto3.services;

import co.edu.usa.Reto3.models.Client;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.usa.Reto3.repositories.ClientRepository;
import java.util.Optional;

/**
 *
 * @author Alexander Arango
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository repoClient;

    public Client saveClient(Client client) {
        Client clientNew = repoClient.save(client);
        return getClientById(clientNew.getIdClient());
    }

    //Read
    public List<Client> getAll() {
        return repoClient.getAll();
    }

    //Update
    public Client updateClient(Client client) {        
        if (client.getIdClient()!= null){
            Optional<Client> clientConsultada = repoClient.getById(client.getIdClient());
            if (clientConsultada.isPresent()){
                
                clientConsultada.get().setAge(client.getAge());
                clientConsultada.get().setEmail(client.getEmail());
                clientConsultada.get().setName(client.getName());
                clientConsultada.get().setPassword(client.getPassword());
                
                return repoClient.save(clientConsultada.get());
            }
        }
        return client;
    }

    //Delete
    public boolean deleteClient(int id) {
        boolean del = repoClient.getById(id).map(client -> {
            repoClient.delete(client);
            return true;
        }).orElse(false);
        return del;
    }

    //Filtro por ID
    public Client getClientById(int id) {
        Optional<Client> client = repoClient.getById(id);
        return client.orElse(new Client());
    }
}
