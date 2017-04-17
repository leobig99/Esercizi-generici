
package parsersax;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UserHandler extends DefaultHandler {

   private boolean bCodice = false;
   private boolean bMonitor = false;
   private boolean bTastiera = false;
   private boolean bMouse = false;
   private ArrayList<Computer> l ;
   private int codice;
   private String monitor, tastiera, mouse;
   

   @Override
   public void startElement(String uri, 
   String localName, String qName, Attributes attributes)
      throws SAXException {
      if (qName.equalsIgnoreCase("codice")) {
         bCodice = true;
      } else if (qName.equalsIgnoreCase("keyboard")) {
         bTastiera = true;
      } else if (qName.equalsIgnoreCase("mouse")) {
         bMouse = true;
      }  else if (qName.equalsIgnoreCase("monitor")) {
         bMonitor = true;
      }
   }

   @Override
   public void endElement(String uri, 
   String localName, String qName) throws SAXException {
      if (qName.equalsIgnoreCase("computer")) {
         System.out.println("End Element :" + qName);
         Computer pc = new Computer(codice, tastiera, mouse, monitor);
         l.add(pc);
      } else {
         System.out.println("End Element :" + qName); 
      }
   }

   @Override
   public void characters(char ch[], 
      int start, int length) throws SAXException {
      if (bCodice) {
          codice = Integer.parseInt( new String(ch, start, length));
          System.out.println("codice: " + codice);
         bCodice = false;
      } else if (bTastiera) {
         tastiera = new String(ch, start, length);
         System.out.println("tastiera: " + tastiera);
         bTastiera = false;
      } else if (bMouse) {
          mouse = new String(ch, start, length);
         System.out.println("mouse: " + mouse );
         bMouse = false;
      } else if (bMonitor) {
          monitor = new String(ch, start, length);
         System.out.println("monitor: "  + monitor);
         bMonitor = false;
      }
   }

    public ArrayList<Computer> getLista() {
        return l;
    }
   
    public UserHandler() {
        super();
        l = new ArrayList<>();
    }
}