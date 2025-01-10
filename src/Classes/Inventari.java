package Classes;

import java.util.ArrayList;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Inventari {
    private static final long serialVersionUID = 1L;
    
    @Id
    private int id;
    public int diners = 0;
    public ArrayList<ItemInventari> llavors;

    public Inventari(ArrayList<ItemInventari> llavors){
        this.llavors = llavors;
    }
    
    public int getDiners() {
        return diners;
    }

    public void setDiners(int diners) {
        this.diners = diners;
    }

    public ArrayList<ItemInventari> getLlavors() {
        return llavors;
    }

    public void setLlavors(ArrayList<ItemInventari> llavors) {
        this.llavors = llavors;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }
    

}
