/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParseSax;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


/**
 *
 * @author andrea
 */
public class ParseSax {
    public static void main(String[] args) {
        try {
            File inputFile = new File("ListaSpesa.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler();
            saxParser.parse(inputFile, userhandler);
            ArrayList<Articolo> a = userhandler.getLista();
            System.out.println(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
