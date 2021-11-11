/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.Reto3.models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase que modela la tabla computer
 */
@Entity
@Table(name = "computer")
public class Computer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Id unico para identificar cada computador
     */
    private Integer id;
    
    /**
     * Nombre del computador
     */
    private String name;
    
    /**
     * Marca del computador
     */
    private String brand;
    
    /**
     * Ano del computador
     */
    private Integer year;
    
    /**
     * UNa pequena descripcion del computador
     */
    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonIgnoreProperties("computer")
    /**
     * Relacion mucho a uno con la tabla category
     */
    private Category category;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "computer")
    @JsonIgnoreProperties({"computer", "client"})
    /**
     * Relacion uno con muchos con la tabla message
     */
    private List<Message> messages;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "computer")
    @JsonIgnoreProperties({"computer", "client"})
    /**
     * Relacion uno a muchos con la tabla reservations
     */
    private List<Reservation> reservations;

    /**
     * Retorna el id de la computadora
     * @return entero id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * Metodo para establecer el Id del computador
     * @param id Entero
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * Meotodo para retornar el nombre del computador
     * @return String con le nombre del computador
     */
    public String getName() {
        return name;
    }
    
    /**
     * Metodo para establecer el nombre del computador
     * @param name string , que contiene el nombre del computador
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * MEtodo que retorna la marca del computador
     * @return string con la marca del computador
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Metodo para establecer la marca del computador
     * @param brand string con la marca
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Retorna el ano del computador
     * @return entero con el ano del computador
     */
    public Integer getYear() {
        return year;
    }
    
    /**
     * Metodo para establecer el ano del computador
     * @param year Entero con el ano del computador
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * Metodo que retorna la descripcion del computador
     * @return string con la descripcion del computador
     */
    public String getDescription() {
        return description;
    }

    /**
     * Metodo para establecer la descripcion del computador
     * @param description String con la descripcion del computador
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Metodo para obtener la gategoria asociada al computador
     * @return Objeto tipo categoria
     */
    public Category getCategory() {
        return category;
    }
    
    /**
     * Metodo para establecer la categoria del computador
     * @param category Objeto categoria 
     */
    public void setCategory(Category category) {
        this.category = category;
    }
    
    /**
     * Meotodo para obtener los mensajes asociados al computador
     * @return lista de objetos tipo Mensaje
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Metodo para establecer la lista de mensajes asociados al computador
     * @param messages Lista de objetos tipo mensaje
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * Metodo para obatener las reservacion asociadas al computador
     * @return Lista de objetos tipo Mensaje
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Metodo para establecer la lista de reservaciones asociadas al computador
     * @param reservations Lista de objetos tipo reservacion
     */
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

}
