/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrea.zoccarato
 */
public class OperazioniDB {

    private Connection conn;

    public OperazioniDB() throws ClassNotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\andrea\\Desktop\\InterrogazioniJSP\\database.db");
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String login(String username, String password) {
        try {
            Statement stm = conn.createStatement();
            //controllo se studente
            String sql = "SELECT tblStudente.* "
                    + "FROM tblStudente INNER JOIN tblCredenziali ON tblStudente.IdCredenziali = tblCredenziali.ID "
                    + "WHERE Username='" + username + "' AND Password='" + password + "'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                return "Studente";
            }
            //controllo se Docente
            String sql1 = "SELECT tblDocente.* "
                    + "FROM tblDocente INNER JOIN tblCredenziali ON tblDocente.IdCredenziali=tblCredenziali.ID "
                    + "WHERE Username='" + username + "' AND Password='" + password + "'";
            ResultSet rs1 = stm.executeQuery(sql1);
            if (rs1.next()) {
                return "Docente";
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Nessuno";
    }

    public ArrayList<String> getStudenti() throws SQLException {
        Statement stm = conn.createStatement();
        ArrayList<String> ret = new ArrayList<>();
        String sql = "SELECT Nome,Cognome "
                + "FROM tblStudente "
                + "ORDER BY Cognome";
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            String nome = rs.getString("Nome");
            String cognome = rs.getString("Cognome");
            ret.add(cognome + "-" + nome);
        }
        return ret;
    }

    public String setInterrogazione(String data, String nomeStudente) throws SQLException {
        Statement stm = conn.createStatement();
        if (data.equals("gg/mm/aaaa")) {
            return "data non valida";
        }
        if (nomeStudente == null) {
            return "Studente non valido";
        }
        String matricola = getMatricolaStud(nomeStudente);
        //controllo se lo studente è già impegnato
        if (studenteGiaImpegnato(matricola)) {
            return "Studente già impegnato";
        }
        String qryPosti = "SELECT Posti "
                + "FROM tblGiornata "
                + "WHERE Giorno='" + data + "'";
        ResultSet rs = stm.executeQuery(qryPosti);
        //se non ci sono risultati allora il giorno è libero
        if (!rs.next()) {
            aggiungiGiornata(data, 3);
            aggiungiInterrogazione(data, matricola, getGirone());
            return "Insterrogazione inserita con successo";
            //altrimenti controllo che ci siano posti liberi
        } else {
            int posti = rs.getInt("Posti");
            if (posti == 0) {
                return "Posti Esauriti";
            }
            aggiungiGiornata(data, posti);
            aggiungiInterrogazione(data, matricola, getGirone());
            return "Insterrogazione inserita con successo";
        }
    }

    public void aggiungiInterrogazione(String data, String matricola, int girone) throws SQLException {
        String sqlInterrogazione = "INSERT INTO tblInterrogazione(Girone,Matricola,Giorno) VALUES('"
                + girone + "',"
                + matricola + "',"
                + data + "');";
        PreparedStatement pstm = conn.prepareStatement(sqlInterrogazione);
        pstm.executeUpdate();
    }

    public void aggiungiGiornata(String data, int posti) throws SQLException {
        String sqlGiornata = "INSERT INTO tblGiornata VALUES('"
                + data + "'," + (posti - 1) + ");";
        PreparedStatement pstm = conn.prepareStatement(sqlGiornata);
        pstm.executeUpdate();
    }

    public String getMatricolaStud(String nominativo) throws SQLException {
        Statement s = conn.createStatement();
        String[] arr = nominativo.split("-");
        System.out.println(Arrays.toString(arr));
        String cognome = arr[0];
        String nome = arr[1];
        System.out.println("Nome:" + nome);
        String sql = "SELECT Matricola "
                + "FROM tblStudente "
                + "WHERE Nome='" + nome + "' AND Cognome='" + cognome + "'";
        ResultSet rs = s.executeQuery(sql);
        String matricola = rs.getString("Matricola");
        System.out.println(matricola);
        return matricola;
    }

    public boolean studenteGiaImpegnato(String matricola) throws SQLException {
        Statement s = conn.createStatement();
        String sql = "SELECT * "
                + "FROM tblInterrogazione "
                + "WHERE Matricola='" + matricola + "'";
        ResultSet rs = s.executeQuery(sql);
        return rs.next();
    }

    public boolean existStudente(String nominativo) throws SQLException {
        Statement s = conn.createStatement();
        String matricola = getMatricolaStud(nominativo);
        String sql = "SELECT * "
                + "FROM tblStudente "
                + "WHERE Matricola='" + matricola + "'";
        ResultSet rs = s.executeQuery(sql);
        return rs.next();
    }

    public int getGirone() throws SQLException {
        Statement s = conn.createStatement();
        String sql = "SELECT count(*)as numStudenti "
                + "FROM tblStudente "
                + "GROUP BY Matricola";
        ResultSet rs = s.executeQuery(sql);
        int numStudenti = rs.getInt("numStudenti");
        String qryGetTopGirone = "SELECT Girone "
                + "FROM tblInterrogazione "
                + "ORDER BY Girone DESC "
                + "LIMIT 1";
        ResultSet rsTopGirone = s.executeQuery(qryGetTopGirone);
        int maxGirone = rsTopGirone.getInt("Girone");
        String qryNumInt = "SELECT Count(*)as numInterrogati "
                + "FROM tblInterrogazione "
                + "WHERE Girone=" + maxGirone + " "
                + "GROUP BY ID";
        ResultSet rsInt = s.executeQuery(qryNumInt);
        int numInt = rsInt.getInt("numInterrogati");
        if (numStudenti == numInt) {
            return numInt + 1;
        } else if (numStudenti > numInt) {
            return maxGirone;
        }
        return 1;
    }

    public ArrayList<String> getInterrogazioni(int girone) throws SQLException {
        ArrayList<String> res = new ArrayList<>();
        Statement stm = conn.createStatement();
        String qry = "SELECT Nome,Cognome,Giorno "
                + "FROM tblStudente INNER JOIN tblInterrogazione ON tblStudente.Matricola=tblInterrogazione.Matricola "
                + "WHERE Girone=" + girone + " "
                + "ORDER BY Cognome";
        ResultSet rs = stm.executeQuery(qry);
        while (rs.next()) {
            String nome = rs.getString("Nome");
            String cognome = rs.getString("Cognome");
            String data = rs.getString("Giorno");
            res.add(cognome + "-" + nome + "-" + data);
        }
        return res;
    }

    public void insertStudente(String nome, String cognome, int età, String username, String password) throws SQLException {
        String sqlCred = "INSERT INTO tblCredenziali(Username,Password) VALUES ("
                + "'" + username + "',"
                + "'" + password + "');";
        PreparedStatement pstm = conn.prepareStatement(sqlCred);
        pstm.executeUpdate();
        String qryIdCred = "SELECT ID "
                + "FROM tblCredenziali "
                + "WHERE Username='" + username + "' AND Password='" + password + "'";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(qryIdCred);
        int idCred = rs.getInt("ID");
        String sqlStud = "INSERT INTO tblStudente(Nome,Cognome,eta,IdCredenziali) VALUES("
                + "'" + nome + "',"
                + "'" + cognome + "',"
                + "'" + età + "',"
                + "'" + idCred + "');";
        PreparedStatement pstm2 = conn.prepareStatement(sqlStud);
        pstm2.executeUpdate();
    }
}
