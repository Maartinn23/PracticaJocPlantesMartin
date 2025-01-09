/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TipusDePlantes;

import Classes.Partida;


/**
 *
 * @author joelg
 */

public class TipusDePlanta {

    static Partida p;
    public String nomTipus;
    public String nomLlavor;
    public int valorDeVenda;
    public int fila;
    public int columna;
    public String explicacioEfecte;
    
    
    public String getEstat(int edat){
        return "";
    }
    
    public void efecte(){
        
    }
    
    public int getValorDeVenda() {
        return 0;
    }
    
    @Override
    public String toString(){
        return "";
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


