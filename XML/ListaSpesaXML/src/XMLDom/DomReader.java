/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLDom;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author andrea
 */
public class DomReader {
    public static void main(String[] args) {
        String filename = "ListaSpesa.xml";
        DomReader ddp = new DomReader();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            File xmlFile = new File(filename);
            Document document = builder.parse(xmlFile);
            ddp.printNodeInfo(document);
        } catch (SAXException sxe) {
            Exception x = sxe;
            if (sxe.getException() != null) {
                x = sxe.getException();
            }
            x.printStackTrace();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    /**
     * Stampa le info sui nodi, in modo ricorsivo
     *
     * @param currentNode il nodo corrente
     */
    public void printNodeInfo(Node currentNode) {
        short sNodeType = currentNode.getNodeType();
            String sNodeName;
            String sNodeValue;        
        //Se è di tipo Element ricavo le informazioni e le stampo
        if (sNodeType == Node.DOCUMENT_NODE ) {
            sNodeName = currentNode.getNodeName();
            sNodeValue = searchTextInElement(currentNode);
            Document d = (Document)currentNode;
            String xtrXMLVersion = d.getXmlVersion();
            String xtrXMLEncoding = d.getXmlEncoding();
            System.out.println("Documento ");  
            System.out.println("version= "+xtrXMLVersion);
            System.out.println("encoding= "+xtrXMLEncoding);
            System.out.println("**********");

        } else if (sNodeType == Node.ELEMENT_NODE) {
            sNodeName = currentNode.getNodeName();
            sNodeValue = searchTextInElement(currentNode);
            NamedNodeMap nnmAttributes = currentNode.getAttributes();
            System.out.println("Elemento: " + sNodeName);
            System.out.println("Attributi: "
                    + printAttributes(nnmAttributes));
            if (!sNodeValue.trim().equalsIgnoreCase("")) {
                System.out.println("Contenuto: " + sNodeValue);
            }
            System.out.print("\n");
        }
        int iChildNumber = currentNode.getChildNodes().getLength();
        //Se non si tratta di una foglia continua l'esplorazione
        if (currentNode.hasChildNodes()) {
            NodeList nlChilds = currentNode.getChildNodes();
            for (int iChild = 0; iChild < iChildNumber; iChild++) {
                printNodeInfo(nlChilds.item(iChild));
            }
        }
    }

    /*
     * Search the content for a given Node
     */
    private static String searchTextInElement(Node elementNode) {
        String sText = "";
        if (elementNode.hasChildNodes()) {
            //Il child node di tipo testo è il primo
            Node nTextChild = elementNode.getChildNodes().item(0);
            sText = nTextChild.getNodeValue();
        }
        return sText;
    }

    private static String printAttributes(NamedNodeMap nnm) {
        String sAttrList = new String();
        if (nnm != null && nnm.getLength() > 0) {
            for (int iAttr = 0; iAttr < nnm.getLength(); iAttr++) {
                sAttrList += nnm.item(iAttr).getNodeName();
                sAttrList += "=";
                sAttrList += nnm.item(iAttr).getNodeValue();
                sAttrList += "; ";
            }
            return sAttrList;
        } else {
            return "assenti";
        }
    }
}
