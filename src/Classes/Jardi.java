package Classes;


import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Jardi implements Serializable{
    
    @Id
    public int id;    
    public Casella[][] mapa = new Casella[10][10];

    public Casella[][] getMapa() {
        return mapa;
    }

    public void setMapa(Casella[][] mapa) {
        this.mapa = mapa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
