/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author andrea
 */
public class Promemoria {

    private String descrizione;
    private Date data;
    private Time ora;
    private int durata;
    private Date preavviso;
    private Date ricorrenza;
    private boolean scaduto;
    private int idPersona;

    public Promemoria(String descrizione, Date data, Time ora, int durata, Date preavviso, Date ricorrenza, boolean scaduto, int idPersona) {
        this.descrizione = descrizione;
        this.data = data;
        this.ora = ora;
        this.durata = durata;
        this.preavviso = preavviso;
        this.ricorrenza = ricorrenza;
        this.scaduto = scaduto;
        this.idPersona = idPersona;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getOra() {
        return ora;
    }

    public void setOra(Time ora) {
        this.ora = ora;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public Date getPreavviso() {
        return preavviso;
    }

    public void setPreavviso(Date preavviso) {
        this.preavviso = preavviso;
    }

    public Date getRicorrenza() {
        return ricorrenza;
    }

    public void setRicorrenza(Date ricorrenza) {
        this.ricorrenza = ricorrenza;
    }

    public boolean isScaduto() {
        return scaduto;
    }

    public void setScaduto(boolean scaduto) {
        this.scaduto = scaduto;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public String toString() {
        return "Promemoria{" + "descrizione=" + descrizione + ", data=" + data + ", ora=" + ora + ", durata=" + durata + ", preavviso=" + preavviso + ", ricorrenza=" + ricorrenza + ", scaduto=" + scaduto + ", idPersona=" + idPersona + '}';
    }

}
