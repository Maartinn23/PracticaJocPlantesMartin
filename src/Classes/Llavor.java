/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import TipusDePlantes.TipusDePlanta;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Llavor implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    private int id;    
    public String nom;
    public TipusDePlanta tipus;
    
    public Llavor(String nom, TipusDePlanta tipus){
        this.nom = nom;
        this.tipus = tipus;
    }
    
    @Override
    public String toString(){
        return nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TipusDePlanta getTipus() {
        return tipus;
    }

    public void setTipus(TipusDePlanta tipus) {
        this.tipus = tipus;
    }
    
    
        @Override
    public boolean equals(Object o){
        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Llavor)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members 
        Llavor llavor = (Llavor) o;
         return llavor.nom.equals(this.nom);
    }
    
}
