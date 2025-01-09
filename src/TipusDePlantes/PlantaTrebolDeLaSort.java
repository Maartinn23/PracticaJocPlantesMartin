/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TipusDePlantes;

import Classes.Partida;
import Classes.Planta;
import Utilities.ConsoleColors;
import java.util.Random;


/**
 *
 * @author joelg
 */

public class PlantaTrebolDeLaSort extends TipusDePlanta {

    static Partida p;
    
    public PlantaTrebolDeLaSort(){
        nomTipus = "Trebol de la sort";
        explicacioEfecte = "Si està creixent: genera " + ConsoleColors.YELLOW + "entre 3 i 7 diners" + ConsoleColors.RESET + ".\n"
                + "Si està madur: genera " + ConsoleColors.YELLOW + "entre 20 i 30 diners" + ConsoleColors.RESET + ".\n"
                + "Triga 7 dies a madurar.\n"
                + "Efecte especial: Té un 7% de possibilitats de generar " + ConsoleColors.YELLOW + "77 diners" + ConsoleColors.RESET + ".";
        nomLlavor = "Llavor de " + ConsoleColors.GREEN + "Trebol de la sort" + ConsoleColors.RESET;
    }

    public PlantaTrebolDeLaSort(Partida p) {
        PlantaTrebolDeLaSort.p = p;
        nomTipus = "Trebol de la sort";
        explicacioEfecte = "Si està creixent: genera " + ConsoleColors.YELLOW + "entre 3 i 7 diners" + ConsoleColors.RESET + ".\n"
                + "Si està madur: genera " + ConsoleColors.YELLOW + "entre 20 i 30 diners" + ConsoleColors.RESET + ".\n"
                + "Triga 7 dies a madurar.\n"
                + "Efecte especial: Té un 7% de possibilitats de generar " + ConsoleColors.YELLOW + "77 diners" + ConsoleColors.RESET + ".";
        nomLlavor = "Llavor de " + ConsoleColors.GREEN + "Trebol de la sort" + ConsoleColors.RESET;
    }

    public PlantaTrebolDeLaSort(int fila, int columna) {
        nomTipus = "Trebol de la sort";
        this.fila = fila;
        this.columna = columna;
        explicacioEfecte = "Si està creixent: genera " + ConsoleColors.YELLOW + "entre 3 i 7 diners" + ConsoleColors.RESET + ".\n"
                + "Si està madur: genera " + ConsoleColors.YELLOW + "entre 20 i 30 diners" + ConsoleColors.RESET + ".\n"
                + "Triga 7 dies a madurar.\n"
                + "Efecte especial: Té un 7% de possibilitats de generar " + ConsoleColors.YELLOW + "77 diners" + ConsoleColors.RESET + ".";

        nomLlavor = "Llavor de " + ConsoleColors.GREEN + "Trebol de la sort" + ConsoleColors.RESET;
    }

    @Override
    public String getEstat(int edat) {
        if (edat <= 7) {
            return "Creixent";
        } else {
            return "Madur";
        }
    }

    @Override
    public void efecte() {
        // Efecte del girasol:
        // Si està creixent: retorna entre 3 i 7
        // Si està madur: retorna entre 20 i 30
        // Té un 7% de possibilitats de donar 77 diners més
        Planta planta = p.jardi.mapa[fila][columna].planta;
        String estat = planta.tipus.getEstat(planta.edat);
        int total = 0;

        Random rand = new Random();

        if (estat.equals("Creixent")) {
            total += rand.nextInt(3, 8);
        } else {
            total += rand.nextInt(20, 31);
        }

        int chance = rand.nextInt(100);
        if (chance < 7) {
            total += 77;
        }

        p.inventari.diners += total;

    }

    @Override
    public String toString() {
        Planta planta = p.jardi.mapa[fila][columna].planta;
        String estat = planta.tipus.getEstat(planta.edat);
        String result = ConsoleColors.GREEN;
        if (estat.equals("Creixent")) {
            return result + "t" + ConsoleColors.RESET;
        } else {
            return result + "T" + ConsoleColors.RESET;
        }
    }

    @Override
    public int getValorDeVenda() {
        Planta planta = p.jardi.mapa[fila][columna].planta;
        String estat = planta.tipus.getEstat(planta.edat);

        if (estat.equals("Creixent")) {
            return 7;
        } else {
            return 77;
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

}
