/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import TipusDePlantes.TipusDePlanta;
import java.io.Serializable;
import javax.persistence.Id;

/**
 *
 * @author joelg
 */

public class Planta implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private int id;
    public TipusDePlanta tipus;
    public int edat;
    
    public Planta(TipusDePlanta tipus, int edat){
        this.tipus = tipus;
        this.edat = edat;
    }
    
    @Override
    public String toString(){
        return tipus.toString();
    }

    public TipusDePlanta getTipus() {
        return tipus;
    }

    public void setTipus(TipusDePlanta tipus) {
        this.tipus = tipus;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
