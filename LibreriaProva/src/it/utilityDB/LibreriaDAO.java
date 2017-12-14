package it.utilityDB;

import entity.Autore;
import entity.Libreria;
import entity.Libro;
import static it.utilityDB.OperazioniDB.getResult;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author carlosalvagno
 */
public class LibreriaDAO {

    private Connection conn = ConnessioneDB.getConnection();

    public static ArrayList<Libreria> loadLibrary() {
        try {
            String query = "SELECT  * from tblLibreria";
            ArrayList<Libreria> lib = new ArrayList<>();
            ArrayList arr;
            arr = OperazioniDB.getRow("tblLibreria", null, null, null); //OperationManager.getIstance().getResults(query, 0);
            for (Object o : arr) {
                Libreria d = new Libreria();
                d.setByDB((ArrayList) o);
                lib.add(d);
            }
            return lib;

        } catch (Exception ex) {
            Logger.getLogger(LibreriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<Autore> loadAutoriLibro(int idLibro) {
        ArrayList app = new ArrayList();
        try {
            app = OperazioniDB.getResult("Select tblAutore.* FROM tblAutore inner join tbrLibroAutore on "
                    + " tblAutore.CodiceAutore = tbrLibroAutore.CodiceAutore WHERE tbrLibroAutore.CodiceLibro = " + idLibro);

        } catch (SQLException e) {
            Logger.getLogger(LibreriaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        ArrayList<Autore> authors = new ArrayList<Autore>();
        for (int i = 0; i < app.size(); i++) {
            ArrayList elem = (ArrayList) app.get(i);
            Autore a = new Autore();
            a.setByDB(elem);
            authors.add(a);

        }
        return authors;
    }

    public static ArrayList<Pair<Libro, Integer>> loadLibriDisponibili(int idLibreria) {
        Connection conn;
        PreparedStatement pstm;
        Libro l;
        int copieDisponibili;
        String strCommand = "Select tblLibro.*, Copie "
                + "From tblLibro inner join tblScorta on tblLibro.CodiceLibro = tblScorta.CodiceLibro "
                + "Where CodiceLibreria = ?";

        ArrayList<Pair<Libro, Integer>> app = new ArrayList<Pair<Libro, Integer>>();
        try {
            conn = ConnessioneDB.getConnection();
            pstm = conn.prepareStatement(strCommand);
            pstm.setInt(1, idLibreria);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                copieDisponibili = rs.getInt("Copie");
                if (copieDisponibili > 0) {
                    l = new Libro(rs.getString("Titolo"), rs.getInt("CodiceGenere"),
                            rs.getString("Editore"), rs.getDouble("Prezzo"), rs.getInt("CodiceLibro"));
                    app.add(new Pair(l, copieDisponibili));
                }
            }
            pstm.close();
        } catch (SQLException e) {
            Logger.getLogger(LibreriaDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return app;
    }

    public static String loadDescrizioneGenere(int idGenere) {
        Connection conn;
        PreparedStatement pstm;
        String strCommand = "Select  Descrizione From tblGenere "
                + "Where CodiceGenere = ?";

        String descrizione = "";
        try {
            conn = ConnessioneDB.getConnection();
            pstm = conn.prepareStatement(strCommand);
            pstm.setInt(1, idGenere);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                descrizione = rs.getString("Descrizione");
            }
            pstm.close();
        } catch (SQLException e) {
            Logger.getLogger(LibreriaDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return descrizione;
    }

    public static void registraVenditaLibro(int codLibreria, String titolo) {
        try {
            //creo la connessione col DB
            Connection conn = ConnessioneDB.getConnection();
            //creo lo statement 
            Statement stmt = conn.createStatement();
            //mi prendo tutte le librerie
            ArrayList<Libreria> librerie = loadLibrary();
            //per tutte le librerie
            for (int i = 0; i < librerie.size(); i++) {
                //prendo la libreria corrente
                Libreria l = librerie.get(i);
                if (l.getID() == codLibreria) {
                    //prendo i libri della libreria selezionata
                    ArrayList<Pair<Libro, Integer>> libri = l.getLibriDisponibili();
                    //per tutti i libri
                    for (int j = 0; j < libri.size(); j++) {
                        Libro libro = libri.get(j).getKey();
                        //se il titolo del libro corrente è uguale a quello richiesto
                        if (libro.getTitle().equals(titolo)) {
                            //prendo le copie del libro
                            int copie = libri.get(j).getValue();
                            //se le copie non sono pari a 0
                            if (copie != 0) {
                                //decremento il numero di copie
                                copie--;
                                //prendo il codice del libro
                                int codLibro = libro.getID();
                                //salvo l'operarzione aggiornando il DB con il numero di copie corrette
                                String sql = "UPDATE tblScorta SET copie=" + copie + " WHERE CodiceLibreria=" + codLibreria + " AND CodiceLibro=" + codLibro;
                                stmt.executeUpdate(sql);
                                //prendo con una query le copie vendute
                                String qryCopie = "SELECT Copie FROM tblvendita WHERE CodiceLibro='" + codLibro + "' AND CodiceLibreria='" + codLibreria + "'";
                                ArrayList arr = (ArrayList) getResult(qryCopie).get(0);
                                //incremento le copie vendute
                                int copieVendute = (Integer) arr.get(0) + 1;
                                //salvo l'operarzione aggiornando il DB con il numero di copie corrette
                                String sql2 = "UPDATE tblVendita SET copie=" + copieVendute + " WHERE CodiceLibreria=" + codLibreria + " AND CodiceLibro=" + codLibro;
                                stmt.executeUpdate(sql2);
                                System.out.println("Operazione completata");
                            } else {
                                System.out.println("Copie esaurite");
                            }
                            return;
                        }
                    }
                    System.out.println("Libro non presente nella libreria selezionata");
                    return;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getBookFromType() {
        ArrayList<String> generi = new ArrayList<>();
        try {

            Statement st = conn.createStatement();
            String qryGenere = "SELECT DISTINCT tblGenere.* FROM tblGenere INNER JOIN tblLibro ON tblGenere.CodiceGenere=tblLibro.CodiceGenere INNER JOIN tblVendita ON tblLibro.CodiceLibro=tblVendita.CodiceLibro WHERE Copie>0 ORDER BY tblGenere.CodiceGenere";
            ResultSet rs = st.executeQuery(qryGenere);

            while (rs.next()) {
                String ris = "";
                int codGenere = rs.getInt("CodiceGenere");

                String descrizione = rs.getString("Descrizione");
                ris += "GENERE{ CodiceGenere: " + codGenere + " Descrizione: " + descrizione + " }\n";
                ris += getLibro(codGenere);
                System.out.println(ris);
                generi.add(ris);
            }

            scritturaSuFile(generi);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private String getLibro(int codGenere) {
        try {
            String s = "";

            Statement st = conn.createStatement();
            String qryLibro = "SELECT tblLibro.* "
                    + "FROM tblLibro "
                    + "WHERE CodiceGenere=" + codGenere;
            ResultSet rs = st.executeQuery(qryLibro);
            while (rs.next()) {
                int codLibro = rs.getInt("CodiceLibro");
                String titolo = rs.getString("Titolo");
                String editore = rs.getString("Editore");
                double prezzo = rs.getDouble("Prezzo");
                s += "  LIBRO{ CodiceLibro: " + codLibro + ", Titolo: " + titolo + ", Editore: " + editore + ", Prezzo: " + prezzo + " }\n";
                s += getLibrerie(codLibro);
            }

            return s;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    private String getLibrerie(int codLibro) {
        try {
            String s = "";
            Statement st = conn.createStatement();
            String qryLibrerie = "SELECT tblLibreria.*,tblScorta.Copie "
                    + "FROM tblLibreria INNER JOIN tblScorta ON tblLibreria.CodiceLibreria=tblScorta.CodiceLibreria "
                    + "INNER JOIN tblLibro ON tblLibro.CodiceLibro=tblScorta.CodiceLibro "
                    + "WHERE tblLibro.CodiceLibro=" + codLibro + " AND Copie>0";
            ResultSet rs = st.executeQuery(qryLibrerie);
            while (rs.next()) {
                int codLibreria = rs.getInt("CodiceLibreria");
                String nome = rs.getString("Nome");
                int nCopie = rs.getInt("Copie");
                s += "        LIBRERIA{ CodiceLibreria: " + codLibreria + ", Nome: " + nome + ", COPIE: " + nCopie + " }\n";
            }
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(LibreriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    private void scritturaSuFile(ArrayList generi) {
        try {
            BufferedWriter buff = new BufferedWriter(new FileWriter("FileGeneri.txt"));
            String s = "";
            for (int i = 0; i < generi.size(); i++) {
                s += generi.get(i);
                buff.write(s, 0, s.length());
                buff.newLine();
                buff.flush();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void main(String[] args) {
        LibreriaDAO lib = new LibreriaDAO();
        lib.getBookFromType();
    }

}
