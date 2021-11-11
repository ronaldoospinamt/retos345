/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.Reto3.services;

import co.edu.usa.Reto3.models.Client;
import co.edu.usa.Reto3.models.custom.TopClientes;
import co.edu.usa.Reto3.models.custom.StatusReservacion;
import co.edu.usa.Reto3.repositories.crud.ReservationCrudRepository;
import co.edu.usa.Reto3.models.Reservation;
import co.edu.usa.Reto3.repositories.ReservationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * Service de reservaciones
 * 
 */
@Service
public class ReservationService {

    /**
     * repositorio de reservaciones
     */
    @Autowired
    private ReservationRepository reposReservation;

    /**
     * Guarda una reservacion
     * @param reservation reservación a guardar
     * @return reserva guardada
     */
    public Reservation saveReservation(Reservation reservation) {
        if(reservation.getStatus() == null) {
            reservation.setStatus("created");
        }        
        Reservation reservationNew = reposReservation.save(reservation);
        return getReservationById(reservationNew.getIdReservation());
    }

    /**
     * Obtiene las reservaciones que existen
     * @return listado de reservaciones
     */
    public List<Reservation> getAll() {
        return reposReservation.getAll();
    }

    /**
     * Actualia una reservación
     * @param reservation Reservación a actualizar
     * @return Reservación actualizada o nulo
     */
    public Reservation updateReservation(Reservation reservation) {        
        if (reservation.getIdReservation()!= null){
            Optional<Reservation> reservationConsultada = reposReservation.getById(reservation.getIdReservation()); //Obtenemos reservación por el id
            if (reservationConsultada.isPresent()){
                
                reservationConsultada.get().setDevolutionDate(reservation.getDevolutionDate());
                reservationConsultada.get().setScore(reservation.getScore());
                reservationConsultada.get().setStartDate(reservation.getStartDate());
                reservationConsultada.get().setStatus(reservation.getStatus());
                reservationConsultada.get().setComputer(reservation.getComputer());
                reservationConsultada.get().setClient(reservation.getClient());
                
                return reposReservation.save(reservationConsultada.get());
            }
        }
        return reservation;
    }

    /**
     * Elimina una reservación por el id
     * @param id de la reserva a eliminar 
     * @return true o false
     */
    public boolean deleteReservation(int id) { // id de la reservación a eliminar
        return reposReservation.getById(id).map(reservation -> {
            reposReservation.delete(reservation);
            return true;
        }).orElse(false);
    }

    /**
     * obtiene una reservación por le id de la reservación
     * @param id id de la reservación
     * @return reservación o null
     */
    public Reservation getReservationById(int id) { // id de la reservación a consultar
        Optional<Reservation> reservation = reposReservation.getById(id);
        return reservation.orElse(new Reservation());
    }
    
    /**
     * Genera un reporte de status de las reservaciones
     * @return reporte
     */
    public StatusReservacion getReporteStatusReservaciones() {
        List<Reservation> completed = reposReservation.ReservacionStatus("completed");
        List<Reservation> cancelled = reposReservation.ReservacionStatus("cancelled");
        return new StatusReservacion(completed.size(), cancelled.size());
    }
    
    /**
     * Reporte reservaciones en una rango de tiempo
     * @param datoA fecha inicio
     * @param datoB feha fin
     * @return listado de reservaciones
     */
    public List<Reservation> getReportesTiempoReservaciones(String datoA, String datoB) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date datoUno = new Date();
        Date datoDos = new Date();
        try {
            datoUno = parser.parse(datoA);
            datoDos = parser.parse(datoB);
        } catch (ParseException evt) {
            evt.printStackTrace();
        }
        if (datoUno.before(datoDos)) {
            return reposReservation.ReservacionTiempo(datoUno, datoDos);
        } else {
            return new ArrayList<>();
        }
    }
    
    /**
     * Reporte top clientes
     * @return listado contador de clientes por reservación
     */
    public List<TopClientes> servicioTopClientes() {        
        return reposReservation.getTopClientes();
    }
}
