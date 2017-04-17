/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParseSax;

/**
 *
 * @author andrea
 */
public class Articolo {

    private String nome;
    private double costo;

    public Articolo(String nome, double costo) {
        this.costo = costo;
        this.nome = nome;
    }

    public Articolo() {
        this.nome = "";
        this.costo = 0.0;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Articolo{" + "nome=" + nome + ", costo=" + costo + '}';
    }

}
