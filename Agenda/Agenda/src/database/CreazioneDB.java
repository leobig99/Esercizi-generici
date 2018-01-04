/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrea.zoccarato
 */
public class CreazioneDB {

    private BufferedWriter buff;

    public CreazioneDB() {
        try {
            buff = new BufferedWriter(new FileWriter("comandiCreazioneDB.txt"));
            creaDB();
        } catch (IOException ex) {
            Logger.getLogger(CreazioneDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void creaDB() {
        //creazione DB
        cmd(".open Agenda.sqlite");
        //Attivazione vincoli integrità
        cmd("PRAGMA foreign_keys = ON;");

        //creazione tabella Persona
        cmd("DROP TABLE IF EXISTS tblPersona;");
        cmd("CREATE TABLE tblPersona("
                + "ID INTEGER PRIMARY KEY  AUTOINCREMENT,"
                + "Cognome TEXT,"
                + "Nome TEXT,"
                + "Mail TEXT UNIQUE,"
                + "Username TEXT NOT NULL UNIQUE,"
                + "Password TEXT NOT NULL, "
                + "UNIQUE(Cognome,Nome)"
                + ");");

        //creazione tabella Promemoria
        cmd("DROP TABLE IF EXISTS tblPromemoria;");
        cmd("CREATE TABLE tblPromemoria ("
                + "IdPromemoria INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Descrizione TEXT NOT NULL,"
                + "Data DATE,"
                + "Ora TIME,"
                + "Durata INTEGER,"
                + "Preavviso DATE,"
                + "Ricorrenza DATE,"
                + "Scaduto BOOLEAN DEFAULT FALSE,"
                + "IdPersona INTEGER NOT NULL,"
                + "FOREIGN KEY (IdPersona) REFERENCES tblPersona(ID)"
                + ");");

        //creazione tabella Appuntamenti
        cmd("DROP TABLE IF EXISTS tblAppuntamento;");
        cmd("CREATE TABLE tblAppuntamento("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Scaduto BOOLEAN DEFAULT FALSE,"
                + "NumeroPersone INTEGER NOT NULL,"
                + "IdProm INTEGER NOT NULL,"
                + "FOREIGN KEY (IdProm) REFERENCES tblpromemoria(IdPromemoria)"
                + ");");

        //creazione tabellaRealazione PersonaAppuntamento
        cmd("DROP TABLE IF EXISTS tbrPersonaAppuntamento;");
        cmd("CREATE TABLE tbrPersonaAppuntamento ("
                + "IdPersona INTEGER NOT NULL,"
                + "IdAppuntamento INTEGER NOT NULL,"
                + "PRIMARY KEY (IdPersona,IdAppuntamento),"
                + "FOREIGN KEY (IdAppuntamento) REFERENCES tblAppuntamento(ID),"
                + "FOREIGN KEY (IdPersona) REFERENCES tblPersona(ID)"
                + ");");

        //
    }

    private void cmd(String cmd) {
        try {
            buff.write(cmd);
            buff.newLine();
            buff.flush();
        } catch (IOException ex) {
            Logger.getLogger(CreazioneDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        CreazioneDB cDB = new CreazioneDB();
    }
}
