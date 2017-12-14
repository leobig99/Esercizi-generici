
package applicazione;

import entity.Autore;
import entity.Libreria;
import entity.Libro;
import it.utilityDB.ConnessioneDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.utilityDB.LibreriaDAO;
import javafx.util.Pair;

/**
 *
 * @author carlo
 */
public class GestioneLibrerie {

    public static void main(String[] args) {
        Connection conn;
        Statement st;
        ResultSet rst;
        Logger logger = Logger.getLogger("ProvaDB");
        try {
            conn = ConnessioneDB.getConnection();

            ArrayList<Libreria> vl = LibreriaDAO.loadLibrary();
            for (Libreria l: vl) {
//                System.out.println(l);
              
                System.out.println("Libri Disonibili");
                ArrayList<Pair<Libro, Integer>> ld = l.getLibriDisponibili();
                for (int i=0; i<ld.size(); i++){
                   Pair<Libro, Integer> li = ld.get(i);
                    System.out.println("Libro: "+li.getKey());
                    System.out.println("Copie: "+li.getValue());
                    System.out.println("");
                }

            }

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestioneLibrerie.class.getName()).log(Level.SEVERE, null, ex);
        } 
}

}
