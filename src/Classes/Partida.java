package Classes;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Partida implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    public int id;
    public int dia = 0;
    public Inventari inventari = null;
    public Jardi jardi = null;
    
    @Override
    public String toString(){
        return "Partida: Dia - " + dia;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public Inventari getInventari() {
        return inventari;
    }

    public void setInventari(Inventari inventari) {
        this.inventari = inventari;
    }

    public Jardi getJardi() {
        return jardi;
    }

    public void setJardi(Jardi jardi) {
        this.jardi = jardi;
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
