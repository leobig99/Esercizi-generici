/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalcoloTot;

import ParseSax.Articolo;
import ParseSax.UserHandler;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import static javafx.application.Platform.exit;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author andrea
 */
public class CalcoloTot {
    
    private Double costoTot;
    private Double soldi;
    private Double soldiTot;

    public CalcoloTot(Double soldi) {
        this.soldi = soldi;
        costoTot=0.0;
        soldiTot=0.0;
    }

    public Double getCostoTot() {
        return costoTot;
    }

    public Double getSoldi() {
        return soldi;
    }

    public void setSoldi(Double soldi) {
        this.soldi = soldi;
    }
    
    public void calcola(Double costo){
        Scanner t=new Scanner(System.in);
        costoTot+=costo;
        String s="";
        if(costoTot>soldi){
            System.out.println("----------Soldi insufficienti");
            System.out.println("----------Inserire altri soldi(digitare no per finire la spesa)");
            s=t.next();
            s=s.toLowerCase();
            if(s.equals("no")){
                System.out.println("----------Fine spesa");
                System.out.println("----------Soldi Spesi: "+soldiTot);
                System.exit(0);
            }else{
                double app=Double.parseDouble(s);
                setSoldi(app);
                soldiTot+=costo;
                return;
            }
        }
        else{
            System.out.println("----------Soldi totali spesi: "+soldiTot);
            System.out.println("-----------Soldi Rimanenti: "+(soldi-costoTot));
            soldiTot+=costo;
        }
    }
    
    public static void main(String[]args){
        try {
            File inputFile = new File("ListaSpesa.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            read userhandler = new read(1000.0);
            saxParser.parse(inputFile, userhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
class read extends DefaultHandler{
    private boolean bCosto = false;
    private boolean bNome = false;
    private double costo;
    private String nome;
    private Double soldi;
    private CalcoloTot c;

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
            c.calcola(costo);
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

    public read(Double soldi) {
        super();
        this.soldi=soldi;
        c=new CalcoloTot(soldi);
    }
}
