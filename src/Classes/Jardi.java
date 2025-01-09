package Classes;


import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Jardi {
    
    @Id
    private int id;    
    public Casella[][] mapa = new Casella[10][10];

    public Casella[][] getMapa() {
        return mapa;
    }

    public void setMapa(Casella[][] mapa) {
        this.mapa = mapa;
    }
    
    
}
