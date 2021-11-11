/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.Reto3.models.custom;

public class StatusReservacion {

    private int completed;
    private int cancelled;

    /**
     * @return the completed
     */
    public int getCompleted() {
        return completed;
    }

    /**
     * @param completed the completed to set
     */
    public void setCompleted(int completed) {
        this.completed = completed;
    }

    /**
     * @return the cancelled
     */
    public int getCancelled() {
        return cancelled;
    }

    /**
     * @param cancelled the cancelled to set
     */
    public void setCancelled(int cancelled) {
        this.cancelled = cancelled;
    }

    public StatusReservacion(int completed, int cancelled) {
        this.completed = completed;
        this.cancelled = cancelled;
    }    

}
