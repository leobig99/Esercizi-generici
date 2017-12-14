package entity;

import it.utilityDB.DBEntity;
import java.util.ArrayList;

/**
 *
 * @author Carlo
 */
public class Autore implements DBEntity {
    private int id;
    private String nome, cognome;

    public Autore(int id,  String cognome, String nome) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
    }

    public Autore() {
    }  
    
    @Override
    public boolean setByDB(ArrayList a) {
        try {
            id = (Integer) a.get(0);
            nome = (String) a.get(1);
            cognome = (String) a.get(2);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Autore{" + "id=" + id + ", nome=" + nome + ", cognome=" + cognome + '}';
    }
    
}
