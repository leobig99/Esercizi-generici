/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entity.Appuntamento;
import entity.Persona;
import entity.Promemoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrea
 */
public class OperazioniDB {

    private Connection conn;
    private PreparedStatement pstm;
    private Statement stm;

    public OperazioniDB() {
        try {
            System.out.println("<------------Operazioni Database------------>");
            conn = ConnessioneDB.getConnection();
            stm = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean existPersona(Persona persona) {
        System.out.println("dentro existPersona");
        try {
            String qryExistPersona = "SELECT tblPersona.* "
                    + "FROM tblPersona "
                    + "WHERE Nome='" + persona.getNome() + "' AND Cognome='" + persona.getCognome() + "'";
            ResultSet rs = stm.executeQuery(qryExistPersona);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean existPromemoria(Persona p, String descrizione, Date data, Time ora) {
        try {
            String sql = "SELECT * FROM tblPromemoria "
                    + "WHERE Descrizione='" + descrizione + "' AND data='" + data + "' AND ora='" + ora + "' AND IdPersona=" + getIdPersona(p);
            ResultSet rs = stm.executeQuery(sql);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getIdPersona(Persona p) {
        try {
            String qryID = "SELECT ID FROM tblPersona "
                    + "WHERE Nome='" + p.getNome() + "' AND Cognome='" + p.getCognome() + "' AND Password='" + p.getPassword() + "' AND Username='" + p.getUsername() + "'";
            ResultSet rs = stm.executeQuery(qryID);
            rs.next();
            return rs.getInt("ID");
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int getIdPromemoria(Persona p, String descrizione, Date data, Time ora) {
        try {
            String qryId = "SELECT IdPromemoria FROM tblPromemoria "
                    + "WHERE Descrizione='" + descrizione + "' AND data='" + data + "' AND ora='" + ora + "' AND IdPersona=" + getIdPersona(p);
            ResultSet rs = stm.executeQuery(qryId);
            rs.next();
            return rs.getInt("IdPromemoria");
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int getIdAppuntamento(Persona p, Appuntamento a) {
        try {
            String qryId = "SELECT ID FROM tblAppuntamento "
                    + "WHERE Scaduto='" + a.isScaduto() + " AND NumeroPersone=" + a.getNumeroPersone() + " AND IdProm=" + a.getIdPromemoria();
            ResultSet rs = stm.executeQuery(qryId);
            rs.next();
            return rs.getInt("ID");
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public Persona getPersona(String username, String password) {
        try {
            String qryPersona = "SELECT * "
                    + "FROM tblPersona "
                    + "WHERE Username='" + username + "' AND Password='" + password + "'";
            ResultSet rs = stm.executeQuery(qryPersona);
            while (rs.next()) {
                String cognome = rs.getString("Cognome");
                String nome = rs.getString("Nome");
                String mail = rs.getString("Mail");
                Persona p = new Persona(cognome, nome, mail, username, password);
                return p;
            }

        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Promemoria> getPromemoriaScadono() {
        ArrayList<Promemoria> ris = new ArrayList<>();
        try {

            Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DATE);
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            String data = "" + year + "-" + month + "-" + day;
            String qryPromByData = "SELECT * "
                    + "FROM tblPromemoria "
                    + "WHERE Preavviso='" + data + "' OR Data='" + data + "'";
            ResultSet rs = stm.executeQuery(qryPromByData);
            while (rs.next()) {
                String Descrizione = rs.getString(2);
                Date Data = rs.getDate(3);
                Time Ora = rs.getTime(4);
                int Durata = rs.getInt(5);
                Date Preavviso = rs.getDate(6);
                Date Ricorrenza = rs.getDate(7);
                boolean Scaduto = rs.getBoolean(8);
                int IdPersona = rs.getInt(9);
                Promemoria p = new Promemoria(Descrizione, Data, Ora, Durata, Preavviso, Ricorrenza, Scaduto, IdPersona);
                ris.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ris;
    }

    public boolean login(String username, String password) {
        System.out.println("Dentro login");
        try {
            String qryLogin = "SELECT * "
                    + "FROM tblPersona "
                    + "WHERE Username='" + username + "' AND Password='" + password + "'";
            ResultSet rs = stm.executeQuery(qryLogin);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean registraPersona(Persona persona) {
        System.out.println("dentro registra persona");
        System.out.println(persona);
        if (!existPersona(persona)) {
            try {
                String sql = "INSERT INTO tblPersona VALUES(NULL,'"
                        + persona.getCognome() + "','"
                        + persona.getNome() + "','"
                        + persona.getMail() + "','"
                        + persona.getUsername() + "','"
                        + persona.getPassword() + "');";
                pstm = conn.prepareStatement(sql);
                pstm.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean registraPromemoria(Persona p, Promemoria prom) {
        if (!existPromemoria(p, prom.getDescrizione(), prom.getData(), prom.getOra())) {
            try {
                String sql = "INSERT INTO tblPromemoria VALUES(NULL,'"
                        + prom.getDescrizione() + "',"
                        + prom.getData() + ",'"
                        + prom.getOra() + "',"
                        + prom.getDurata() + ","
                        + prom.getPreavviso() + ","
                        + prom.getRicorrenza() + ",'"
                        + prom.isScaduto() + "',"
                        + prom.getIdPersona() + ");";
                pstm = conn.prepareStatement(sql);
                pstm.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean registraAppuntamento(Persona p, Appuntamento a) {
        try {
            String sql = "INSERT INTO tblAppuntamento VALUES(NULL,'"
                    + a.isScaduto() + "',"
                    + a.getNumeroPersone() + ","
                    + a.getIdPromemoria() + ");";
            pstm = conn.prepareStatement(sql);
            pstm.executeUpdate();

            String sqlRel = "INSERT INTO tbrPersonaAppuntamento VALUES("
                    + getIdPersona(p) + ","
                    + getIdAppuntamento(p, a) + ");";
            pstm = conn.prepareStatement(sqlRel);
            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean modificaPersona(Persona p, String username, String password) {
        try {
            String sql = "UPDATE tblPersona "
                    + "SET Cognome='" + p.getCognome() + "',"
                    + "Nome='" + p.getNome() + "',"
                    + "Mail='" + p.getMail() + "',"
                    + "Username='" + p.getUsername() + "',"
                    + "Password='" + p.getPassword() + "' "
                    + "WHERE Username='" + username + "' AND Password='" + password + "'";
            pstm = conn.prepareStatement(sql);
            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean modificaAppuntamento(Persona p, Appuntamento a) {
        try {
            String sql = "UPDATE tblAppuntamento SET "
                    + "Scaduto='" + a.isScaduto() + "',"
                    + "NumeroPersone=" + a.getNumeroPersone() + ","
                    + "IdPromemoria=" + a.getIdPromemoria();
            pstm = conn.prepareStatement(sql);
            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean modificaPromemoria(Persona p, Promemoria prom) {
        try {
            String sql = "UPDATE tblPromemoria SET "
                    + "Descrizione='" + prom.getDescrizione() + "',"
                    + "Data=" + prom.getData() + ","
                    + "Ora=" + prom.getOra() + ","
                    + "Durata" + prom.getDurata() + ","
                    + "Preavviso=" + prom.getPreavviso() + ","
                    + "Ricorrenza=" + prom.getRicorrenza() + ","
                    + "IdPersona=" + prom.getIdPersona() + "";
            pstm = conn.prepareStatement(sql);
            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Promemoria> visualizzaPromemoria(Persona p) {
        ArrayList<Promemoria> ris = new ArrayList<>();
        try {
            String qryProm = "SELECT * "
                    + "FROM tblPromemoria "
                    + "WHERE  IdPersona=" + getIdPersona(p) + " "
                    + "ORDER BY Data";
            ResultSet rs = stm.executeQuery(qryProm);
            while (rs.next()) {
                String Descrizione = rs.getString(2);
                Date Data = rs.getDate(3);
                Time Ora = rs.getTime(4);
                int Durata = rs.getInt(5);
                Date Preavviso = rs.getDate(6);
                Date Ricorrenza = rs.getDate(7);
                boolean Scaduto = rs.getBoolean(8);
                int IdPersona = rs.getInt(9);
                Promemoria prom = new Promemoria(Descrizione, Data, Ora, Durata, Preavviso, Ricorrenza, Scaduto, IdPersona);
                ris.add(prom);
            }
            return ris;
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ris;
    }

    public ArrayList<Appuntamento> visualizzaAppuntamenti(Persona p) {
        ArrayList<Appuntamento> ris = new ArrayList<>();
        try {
            String qryApp = "SELECT tblAppuntamento.* "
                    + "FROM tblAppuntamento INNER JOIN tbrPersonaAppuntamento ON  tblAppuntamento.ID=tbrPersonaAppuntamento.IdAppuntamento "
                    + "WHERE  tbrPersonaAppuntamento.IdPersona=" + getIdPersona(p);
            ResultSet rs = stm.executeQuery(qryApp);
            while (rs.next()) {
                boolean scaduto = rs.getBoolean("Scaduto");
                int nPersone = rs.getInt("NumeroPersone");
                int idProm = rs.getInt("IdProm");
                Appuntamento a = new Appuntamento(scaduto, nPersone, idProm);
                ris.add(a);
            }
            return ris;
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ris;
    }

    public void rinnova(Persona p) {
        try {
            String sql = "SELECT tblPromemoria.* "
                    + "FROM tblPromemoria "
                    + "WHERE Scaduto=true AND IdPersona=" + getIdPersona(p);
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String Descrizione = rs.getString(2);
                Date Data = rs.getDate(3);
                Time Ora = rs.getTime(4);
                int Durata = rs.getInt(5);
                Date Preavviso = rs.getDate(6);
                Date ricorrenza = rs.getDate(7);
                boolean Scaduto = rs.getBoolean(8);
                int IdPersona = rs.getInt(9);

                Promemoria prom = new Promemoria(Descrizione, ricorrenza, Ora, Durata, Preavviso, null, Scaduto, IdPersona);
                registraPromemoria(p, prom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean cancellaPromemoria(Persona p, String descrizione, Date data, Time ora) {
        if (existPromemoria(p, descrizione, data, ora)) {
            try {
                String sql = "DELETE FROM tblPromemoria "
                        + "WHERE Descrizione='" + descrizione + "' AND data=" + data + " AND ora=" + ora + " AND IdPersona=" + getIdPersona(p);
                pstm = conn.prepareStatement(sql);
                pstm.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(OperazioniDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

}
