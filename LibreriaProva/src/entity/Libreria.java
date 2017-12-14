package entity;

import it.utilityDB.DBEntity;
import it.utilityDB.LibreriaDAO;
import java.util.ArrayList;
import javafx.util.Pair;

public class Libreria implements DBEntity {

    private int id;
    private String name;

    private ArrayList<Pair<Libro, Integer>> libriDisponibili;

    public Libreria() {
    }

    public Libreria(int id, String name) {
        this.id = id;
        this.name = name;
        libriDisponibili = null;
    }

    public ArrayList<Pair<Libro, Integer>> getLibriDisponibili() {
        if (libriDisponibili == null) {
            libriDisponibili = LibreriaDAO.loadLibriDisponibili(id);
        }
        return libriDisponibili;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean setByDB(ArrayList a) {
        try {
            id = (Integer) a.get(0);
            name = (String) a.get(1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[Libreria : " + id + "  " + name + "]";
    }
}
