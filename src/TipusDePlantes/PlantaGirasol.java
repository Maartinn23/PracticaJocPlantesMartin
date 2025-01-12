/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TipusDePlantes;

import Classes.Partida;
import Classes.Planta;
import Utilities.ConsoleColors;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author joelg
 */
@Entity
public class PlantaGirasol extends TipusDePlanta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public int id;
    static Partida p;

    public PlantaGirasol() {
        nomTipus = "Girasol";
        explicacioEfecte = "Si està creixent: genera " + ConsoleColors.YELLOW + "5 diners" + ConsoleColors.RESET + ".\n"
                + "Si està madur: genera " + ConsoleColors.YELLOW + "10 diners" + ConsoleColors.RESET + ".\n"
                + "Triga 3 dies a madurar.\n"
                + "Efecte especial: Genera " + ConsoleColors.YELLOW + "5 diners" + ConsoleColors.RESET + " per cada " + ConsoleColors.YELLOW + "girasol" + ConsoleColors.RESET + " adjacent ortogonalment.";

        nomLlavor = "Llavor de " + ConsoleColors.YELLOW + "girasol" + ConsoleColors.RESET;
    }

    public PlantaGirasol(Partida p) {
        PlantaGirasol.p = p;
        nomTipus = "Girasol";
        explicacioEfecte = "Si està creixent: genera " + ConsoleColors.YELLOW + "5 diners" + ConsoleColors.RESET + ".\n"
                + "Si està madur: genera " + ConsoleColors.YELLOW + "10 diners" + ConsoleColors.RESET + ".\n"
                + "Triga 3 dies a madurar.\n"
                + "Efecte especial: Genera " + ConsoleColors.YELLOW + "5 diners" + ConsoleColors.RESET + " per cada " + ConsoleColors.YELLOW + "girasol" + ConsoleColors.RESET + " adjacent ortogonalment.";

        nomLlavor = "Llavor de " + ConsoleColors.YELLOW + "girasol" + ConsoleColors.RESET;
    }

    public PlantaGirasol(int fila, int columna) {
        nomTipus = "Girasol";
        this.fila = fila;
        this.columna = columna;
        explicacioEfecte = "Si està creixent: genera " + ConsoleColors.YELLOW + "5 diners" + ConsoleColors.RESET + ".\n"
                + "Si està madur: genera " + ConsoleColors.YELLOW + "10 diners" + ConsoleColors.RESET + ".\n"
                + "Triga 3 dies a madurar.\n"
                + "Efecte especial: Genera " + ConsoleColors.YELLOW + "5 diners" + ConsoleColors.RESET + " per cada " + ConsoleColors.YELLOW + "girasol" + ConsoleColors.RESET + " adjacent ortogonalment.";

        nomLlavor = "Llavor de " + ConsoleColors.YELLOW + "girasol" + ConsoleColors.RESET;
    }

    @Override
    public String getEstat(int edat) {
        if (edat <= 3) {
            return "Creixent";
        } else {
            return "Madur";
        }
    }

    @Override
    public void efecte() {
        // Efecte del girasol:
        // Si està creixent: retorna 5 + 5*Girasols adjacents ortogonalment
        // Si està madur: retorna 10 + 5*Girasols adjacents ortogonalment
        Planta planta = p.jardi.mapa[fila][columna].planta;
        String estat = planta.tipus.getEstat(planta.edat);
        int girasolsAdjacents = 0;

        // Comprovem els girasols adjacents
        if (fila - 1 >= 0) {
            if (p.jardi.mapa[fila - 1][columna] != null) {
                if (p.jardi.mapa[fila - 1][columna].planta.tipus.nomTipus.equals("Girasol")) {
                    girasolsAdjacents += 1;
                }
            }
        }
        if (columna - 1 >= 0) {
            if (p.jardi.mapa[fila][columna - 1] != null) {
                if (p.jardi.mapa[fila][columna - 1].planta.tipus.nomTipus.equals("Girasol")) {
                    girasolsAdjacents += 1;
                }
            }
        }
        if (fila + 1 < 10) {
            if (p.jardi.mapa[fila + 1][columna] != null) {
                if (p.jardi.mapa[fila + 1][columna].planta.tipus.nomTipus.equals("Girasol")) {
                    girasolsAdjacents += 1;
                }
            }
        }
        if (columna + 1 < 10) {
            if (p.jardi.mapa[fila][columna + 1] != null) {
                if (p.jardi.mapa[fila][columna + 1].planta.tipus.nomTipus.equals("Girasol")) {
                    girasolsAdjacents += 1;
                }
            }
        }

        // Afegim els diners que toquin
        if (estat.equals("Creixent")) {
            p.inventari.diners += (5 + (5 * girasolsAdjacents));
        } else {
            p.inventari.diners += (10 + (5 * girasolsAdjacents));
        }

    }

    @Override
    public String toString() {
        Planta planta = p.jardi.mapa[fila][columna].planta;
        String estat = planta.tipus.getEstat(planta.edat);
        String result = ConsoleColors.YELLOW;
        if (estat.equals("Creixent")) {
            return result + "g" + ConsoleColors.RESET;
        } else {
            return result + "G" + ConsoleColors.RESET;
        }
    }

    @Override
    public int getValorDeVenda() {
        Planta planta = p.jardi.mapa[fila][columna].planta;
        String estat = planta.tipus.getEstat(planta.edat);

        if (estat.equals("Creixent")) {
            return 5;
        } else {
            return 15;
        }
    }

    public String getNomTipus() {
        return nomTipus;
    }

    public void setNomTipus(String nomTipus) {
        this.nomTipus = nomTipus;
    }

    public String getNomLlavor() {
        return nomLlavor;
    }

    public void setNomLlavor(String nomLlavor) {
        this.nomLlavor = nomLlavor;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getExplicacioEfecte() {
        return explicacioEfecte;
    }

    public void setExplicacioEfecte(String explicacioEfecte) {
        this.explicacioEfecte = explicacioEfecte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Partida getP() {
        return p;
    }

    public static void setP(Partida p) {
        PlantaGirasol.p = p;
    }

}
