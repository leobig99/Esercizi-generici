package entity;

import it.utilityDB.DBEntity;
import it.utilityDB.LibreriaDAO;
import java.util.ArrayList;

public class Libro implements DBEntity {

    private String title, editor, genere;
    private double price;
    private int id, idGenere;
    private ArrayList<Autore> authors;

    public Libro(String title, int idG, String editor, double price, int id) {
        this.title = title;
        this.idGenere = idG;
        this.editor = editor;
        this.price = price;
        this.id = id;
        authors= null;
        genere = null;
    }


    public Libro() {
    }
    
  
    public ArrayList<Autore> getAuthors() {
        if (authors == null) authors = LibreriaDAO.loadAutoriLibro(id);
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public String getGenere() {
        if (genere == null) genere =  LibreriaDAO.loadDescrizioneGenere(idGenere);
        return genere;
    }

    public String getEditor() {
        return editor;
    }

    public int getID() {
        return id;
    }

    public double getPrice() {
        return price;
    }
    
    @Override
    public boolean setByDB(ArrayList a) {
        try {
            id = (Integer) a.get(0);
            title = (String) a.get(1);
            idGenere = (Integer) a.get(2);
            editor = (String) a.get(3);
            price = (Double) a.get(4);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public String toString() {
        return "Libro{" + "title=" + title + ", genere=" + getGenere() + ", editor=" + editor + ", price=" + price +
                     ", id=" + id + ", authors=" + getAuthors() + '}';
    }

    
}
