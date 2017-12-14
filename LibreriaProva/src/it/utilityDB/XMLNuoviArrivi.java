/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.utilityDB;

import entity.Autore;
import entity.Libro;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author andrea
 */
public class XMLNuoviArrivi {

    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document document;
    private Element nuoviArrivi;
    private ArrayList<Pair<Element, Integer>> librerie;
    private ArrayList<Pair<Integer, Pair<ArrayList<Libro>, Element>>> libri;

    public XMLNuoviArrivi() { 
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            document = builder.newDocument();
            librerie = new ArrayList<>();
            libri = new ArrayList<>();
            nuoviArrivi = document.createElement("Nuovi_Arrivi");
            document.appendChild(nuoviArrivi);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLNuoviArrivi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addLibreria(int codLibreria) {
        if (!existLibreria(codLibreria)) {
            Element libreria = document.createElement("CodiceLibreria");
            libreria.setTextContent("" + codLibreria);
            nuoviArrivi.appendChild(libreria);
            librerie.add(new Pair<>(libreria, codLibreria));
            libri.add(new Pair<>(codLibreria, new Pair<>(new ArrayList<>(), document.createElement("Lista_Libri"))));
            salvaModifiche();
        } else {
            System.out.println("Libreria già esistente");
        }
    }

    public void addLibro(int codLibreria, Libro lib) {
        if (!existLibreria(codLibreria)) {
            addLibreria(codLibreria);
        }
        if (!existLibro(codLibreria, lib.getID())) {
            int posLibreria = getPos(codLibreria);
            Element listaLibri = libri.get(posLibreria).getValue().getValue();
            Element libro = document.createElement("Libro");

            Element codice = document.createElement("Codice");
            codice.setTextContent("" + lib.getID());
            libro.appendChild(codice);

            Element titolo = document.createElement("Titolo");
            titolo.setTextContent(lib.getTitle());
            libro.appendChild(titolo);

            Element editore = document.createElement("Editore");
            editore.setTextContent(lib.getEditor());
            libro.appendChild(editore);

            Element prezzo = document.createElement("Prezzo");
            prezzo.setTextContent("" + lib.getPrice());
            libro.appendChild(prezzo);

            Element genere = document.createElement("Genere");
            genere.setTextContent(lib.getGenere());
            libro.appendChild(genere);

            ArrayList<Autore> autori = lib.getAuthors();
            Element listaAutori = document.createElement("Lista_Autori");
            for (int i = 0; i < autori.size(); i++) {
                Autore a = autori.get(i);
                Element autore = document.createElement("Autore");

                Element cognome = document.createElement("Cognome");
                cognome.setTextContent(a.getCognome());
                autore.appendChild(cognome);

                Element nome = document.createElement("Nome");
                cognome.setTextContent(a.getNome());
                autore.appendChild(nome);

                listaAutori.appendChild(autore);
            }
            libro.appendChild(listaAutori);
            listaLibri.appendChild(libro);
            nuoviArrivi.appendChild(listaLibri);

            ArrayList<Libro> app = libri.get(posLibreria).getValue().getKey();
            app.add(lib);
            libri.remove(posLibreria);
            libri.add(posLibreria, new Pair(codLibreria, new Pair<>(app, listaLibri)));

            salvaModifiche();
        } else {
            System.out.println("Libro già presente");
        }
    }

    private boolean existLibro(int codLibreria, int codLibro) {
        ArrayList<Libro> app = libri.get(getPos(codLibreria)).getValue().getKey();
        for (int i = 0; i < app.size(); i++) {
            if (app.get(i).getID() == codLibro) {
                return true;
            }
        }
        return false;
    }

    private int getPos(int codLibreria) {
        for (int i = 0; i < libri.size(); i++) {
            if (libri.get(i).getKey() == codLibreria) {
                return i;
            }
        }
        return -1;
    }

    private boolean existLibreria(int codLibreria) {
        for (int i = 0; i < librerie.size(); i++) {
            if (librerie.get(i).getValue() == codLibreria) {
                return true;
            }
        }
        return false;
    }

    private void salvaModifiche() {
        try {
            TransformerFactory trfactory;
            Transformer transformer;
            DOMSource source;
            StreamResult stream;

            trfactory = TransformerFactory.newInstance();
            transformer = trfactory.newTransformer();
            source = new DOMSource(document);
            stream = new StreamResult(new File("XMLLibreria.xml"));
            transformer.transform(source, stream);
            System.out.println("Salvataggio su file XML completato");
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XMLNuoviArrivi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XMLNuoviArrivi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        XMLNuoviArrivi xml = new XMLNuoviArrivi();
        xml.addLibro(3, new Libro("L'isola Misteriosa", 1, "Marsilio", 12.00, 14));
        xml.addLibro(3, new Libro("Maze Runner-La rivelazione", 5, "Fanucci", 10.00, 15));
        xml.addLibro(3, new Libro("Il vecchio e il mare",99589,"Mondadori",2.00,0));
		xml.addLibreria(4);
    }
}
