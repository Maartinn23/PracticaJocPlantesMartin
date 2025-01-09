/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import javax.persistence.*;

/**
 *
 * @author joelg
 */

public class Casella{
    @Id
    private int id;
    public Planta planta = null;
    public boolean tePlanta = false;
    
    public Casella(Planta planta, boolean tePlanta){
        this.planta = planta;
        this.tePlanta = tePlanta;
    }
    
    public Casella(){
        
    }
    
    @Override
    public String toString(){
        return planta.toString();
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    public boolean isTePlanta() {
        return tePlanta;
    }

    public void setTePlanta(boolean tePlanta) {
        this.tePlanta = tePlanta;
    }
    
    
    
}
