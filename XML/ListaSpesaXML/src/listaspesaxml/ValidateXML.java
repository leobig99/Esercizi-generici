/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listaspesaxml;

import java.io.File;
import java.io.IOException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;



/**
 *
 * @author andrea
 */
public class ValidateXML {

    public static void validate (String XMLDoc, String XSDSchema) throws SAXException, IOException{
        SchemaFactory factory=SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File schemaFile=new File(XSDSchema);
        Schema schema=factory.newSchema(schemaFile);
        Validator v=schema.newValidator();
        Source source=new StreamSource(XMLDoc);
        v.validate(source);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String schema ="SchemaListaSpesa.xsd";
        String doc="ListaSpesa.xml";
        try {
            validate(doc,schema);
            System.out.println("Documento XML valido");
        } catch (SAXException ex) {
            System.out.println("Documento XML non valido");
            System.out.println(ex.getMessage());
        }
    }
    
}
