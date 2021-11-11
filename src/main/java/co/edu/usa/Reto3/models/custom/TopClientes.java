package co.edu.usa.Reto3.models.custom;

import co.edu.usa.Reto3.models.Client;



public class TopClientes {
    
    private Long total;
    private Client client;
    
    //private List<Cliente> clientes;

    /**
     * @return the total
     */
    public Long getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Long total) {
        this.total = total;
    }

     /**
     * @return the clientes
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param cliente the clientes to set
     */
    public void setClient(Client client) {
        this.client = client;
    }
    
    public TopClientes(Long total, Client client) {
        this.total = total; 
        this.client = client;
    }

    public TopClientes() {
        //Empty Constructor
    }    


    
}
