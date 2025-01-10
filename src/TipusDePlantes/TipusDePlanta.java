/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TipusDePlantes;

import Classes.Partida;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author joelg
 */
@Entity
public class TipusDePlanta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    static Partida p;
    public String nomTipus;
    public String nomLlavor;
    public int valorDeVenda;
    public int fila;
    public int columna;
    public String explicacioEfecte;

    public String getEstat(int edat) {
        return "";
    }

    public void efecte() {

    }

    public int getValorDeVenda() {
        return 0;
    }

    @Override
    public String toString() {
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public static Partida getP() {
        return p;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setP(Partida p) {
        TipusDePlanta.p = p;
    }

    public void setValorDeVenda(int valorDeVenda) {
        this.valorDeVenda = valorDeVenda;
    }

}
