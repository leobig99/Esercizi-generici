package domtreetest;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
This program displays an XML document as a tree.
 */
public class DomTest {

    public static String elementString(Element e) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("--->>> Element: <");
        buffer.append(e.getTagName());
        NamedNodeMap attributes = e.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            buffer.append(' ');
            Node attributeNode = attributes.item(i);
            buffer.append(attributeNode.getNodeName());
            buffer.append('=');
            buffer.append('"');
            buffer.append(attributeNode.getNodeValue());
            buffer.append('"');
        }
        buffer.append('>');
        return buffer.toString();
    }

    public static String characterString(CharacterData node) {
        StringBuffer buffer = new StringBuffer(node.getData());
        for (int i = 0; i < buffer.length(); i++) {
            if (buffer.charAt(i) == '\r') {
                buffer.replace(i, i + 1, "\\r");
                i++;
            } else if (buffer.charAt(i) == '\n') {
                buffer.replace(i, i + 1, "\\n");
                i++;
            } else if (buffer.charAt(i) == '\t') {
                buffer.replace(i, i + 1, "\\t");
                i++;
            }
        }
        if (node instanceof Text) {
            buffer.insert(0, "--->>> Text: ");
        } else if (node instanceof Comment) {
            buffer.insert(0, "--->>> Comment: ");
        } else if (node instanceof CDATASection) {
            buffer.insert(0, "--->>> CDATASection: ");
        }

        return buffer.toString();
    }

    public static void visNodo(Object value) {
        Node node = (Node) value;
        NodeList list;
        String str = "";
        if (node instanceof Element) {
            str = elementString((Element) node);
        } else if (node instanceof CharacterData) {
            str = characterString((CharacterData) node);
        } else {
            str = node.getClass() + ": " + node.toString();
        }

        System.out.println(str);

        list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            visNodo(list.item(i));
        }


    }

    public static ArrayList<Element> estrai(Document doc, String nomeProp, String valueProp) {
        ArrayList<Element> ris = new ArrayList<Element>();

        NodeList l= doc.getElementsByTagName(nomeProp);
        for (int i = 0; i < l.getLength(); i++) {

            Element e=(Element)l.item(i);
            Node n = e.getFirstChild();
            System.out.println("Tipo mouse: "+n.getNodeValue());
            if (n.getNodeType() == Node.TEXT_NODE) {
                if ( ((String)n.getNodeValue()).equals(valueProp)) {
                    ris.add ((Element)e.getParentNode());
                }
            }
        }
        return ris;
    }

    public static void visualizzaCodici (ArrayList<Element> l) {
//        System.out.println(""+l.size());
         for (int i = 0; i < l.size(); i++) {
            Element e=l.get(i);
            System.out.println(""+e.getElementsByTagName("codice").item(0).getFirstChild().getNodeValue());
        }
    }

    public static void main(String[] args) throws Exception {
        final String outputEncoding = "UTF-8";
        File f = new File("computers.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        factory.setNamespaceAware(true);
//        factory.setValidating(true);
//        factory.setIgnoringElementContentWhitespace(true);
//        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
//                "http://www.w3.org/2001/XMLSchema");
//        OutputStreamWriter errorWriter = new OutputStreamWriter(System.err,
//                outputEncoding);

        DocumentBuilder builder = factory.newDocumentBuilder();
//        builder.setErrorHandler(new MyErrorHandler(new PrintWriter(errorWriter, true)));
        Document doc = builder.parse(f);
        Element root = doc.getDocumentElement();
        // visNodo(root);
        System.out.println("Visualizza codici dei computers con 3 button");
        ArrayList<Element> el=estrai(doc, "mouse", "3 button");
        visualizzaCodici (el);

    }
}

class MyErrorHandler implements ErrorHandler {

    private PrintWriter out;

    MyErrorHandler(PrintWriter out) {
        this.out = out;
    }

    private String getParseExceptionInfo(SAXParseException spe) {
        String systemId = spe.getSystemId();
        if (systemId == null) {
            systemId = "null";
        }

        String info = "URI=" + systemId + " Line=" + spe.getLineNumber()
                + ": " + spe.getMessage();
        return info;
    }

    public void warning(SAXParseException spe) throws SAXException {
        out.println("Warning: " + getParseExceptionInfo(spe));
    }

    public void error(SAXParseException spe) throws SAXException {
        String message = "Error: " + getParseExceptionInfo(spe);
        throw new SAXException(message);
    }

    public void fatalError(SAXParseException spe) throws SAXException {
        String message = "Fatal Error: " + getParseExceptionInfo(spe);
        throw new SAXException(message);
    }
}
