/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author joelg
 */
@Entity
public class ItemInventari {
    private static final long serialVersionUID = 1L;
    
    @Id
    private int id;
    public Llavor llavor;
    public int quantitat;
    public Inventari inventari;
    
    public ItemInventari(Llavor llavor, int quantitat, Inventari inventari){
        this.llavor = llavor;
        this.quantitat = quantitat;
        this.inventari = inventari;
    }

    public Llavor getLlavor() {
        return llavor;
    }

    public void setLlavor(Llavor llavor) {
        this.llavor = llavor;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public Inventari getInventari() {
        return inventari;
    }

    public void setInventari(Inventari inventari) {
        this.inventari = inventari;
    }
    
    @Override
    public boolean equals(Object o){
        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof ItemInventari)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members 
        ItemInventari ii = (ItemInventari) o;
         return ii.llavor.nom.equals(this.llavor.nom);
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
