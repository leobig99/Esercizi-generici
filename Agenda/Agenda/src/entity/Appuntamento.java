/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author andrea
 */
public class Appuntamento {

    private boolean scaduto;
    private int numeroPersone;
    private int idPromemoria;

    public Appuntamento(boolean scaduto, int numeroPersone, int idPromemoria) {
        this.scaduto = scaduto;
        this.numeroPersone = numeroPersone;
        this.idPromemoria = idPromemoria;
    }

    public boolean isScaduto() {
        return scaduto;
    }

    public void setScaduto(boolean scaduto) {
        this.scaduto = scaduto;
    }

    public int getNumeroPersone() {
        return numeroPersone;
    }

    public void setNumeroPersone(int numeroPersone) {
        this.numeroPersone = numeroPersone;
    }

    public int getIdPromemoria() {
        return idPromemoria;
    }

    public void setIdPromemoria(int idPromemoria) {
        this.idPromemoria = idPromemoria;
    }

    @Override
    public String toString() {
        return "Appuntamento{" + "scaduto=" + scaduto + ", numeroPersone=" + numeroPersone + ", idPromemoria=" + idPromemoria + '}';
    }


}
