/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.Reto3.repositories;

import co.edu.usa.Reto3.models.Client;
import co.edu.usa.Reto3.models.custom.TopClientes;
import co.edu.usa.Reto3.models.Reservation;
import co.edu.usa.Reto3.repositories.crud.ReservationCrudRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * 
 */
@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCrudRepository repo;

    public List<Reservation> getAll() {
        return (List<Reservation>) repo.findAll();
    }

    public Reservation save(Reservation reservation) {
        return repo.save(reservation);
    }

    public Optional<Reservation> getById(int id) {
        return repo.findById(id);
    }

    public String deleteById(int id) {
        repo.deleteById(id);
        return "Registro con id " + id + " ha sido eliminado";
    }

    public void delete(Reservation reservation) {
        repo.delete(reservation);
    }

    public List<Reservation> ReservacionStatus(String status) {
        return repo.findAllByStatus(status);
    }

    public List<Reservation> ReservacionTiempo(Date a, Date b) {
        return repo.findAllByStartDateAfterAndStartDateBefore(a, b);
    }

    public List<TopClientes> getTopClientes() {
        List<TopClientes> res = new ArrayList<>();
        List<Object[]> report = repo.countTotalReservationsByClient();
        
        for (int i = 0; i < report.size(); i++) {
            res.add(new TopClientes((Long) report.get(i)[1], (Client) report.get(i)[0]));

        }
        return res;
    }
}
