/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParseSax;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author andrea
 */
public class UserHandler extends DefaultHandler {

    private boolean bCosto = false;
    private boolean bNome = false;
    private ArrayList<Articolo> l;
    private double costo;
    private String nome;

    @Override
    public void startElement(String uri,
            String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase("nome")) {
            bNome = true;
        } else if (qName.equalsIgnoreCase("costo")) {
            bCosto = true;
        }
        System.out.println("Start Element:" + qName);
    }

    @Override
    public void endElement(String uri,
            String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("articolo")) {
            System.out.println("End Element :" + qName);
            Articolo pc = new Articolo(nome, costo);
            l.add(pc);
        } else {
            System.out.println("End Element :" + qName);
        }
    }

    @Override
    public void characters(char ch[],
            int start, int length) throws SAXException {
        if (bNome) {
            nome = new String(ch, start, length);
            System.out.println("nome: " + nome);
            bNome = false;
        } else if (bCosto) {
            costo = Double.parseDouble(new String(ch, start, length));
            System.out.println("costo: " + costo);
            bCosto = false;
        }
    }

    public ArrayList<Articolo> getLista() {
        return l;
    }

    public UserHandler() {
        super();
        l = new ArrayList<>();
    }

}
