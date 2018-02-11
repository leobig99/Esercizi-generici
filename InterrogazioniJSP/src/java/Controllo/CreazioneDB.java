/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllo;

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

    BufferedWriter buff;

    public CreazioneDB() {
        try {
            buff = new BufferedWriter(new FileWriter("creaDB.txt"));
        } catch (IOException ex) {
            Logger.getLogger(CreazioneDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void creaDB() {
        scriviSuFile(".open database.db");
        scriviSuFile("PRAGMA foreign_keys=ON;");

        scriviSuFile("DROP TABLE IF EXISTS tblStudente;");
        scriviSuFile("CREATE TABLE tblStudente("
                + "Matricola TEXT primary key NOT NULL,"
                + "Nome TEXT NOT NULL,"
                + "Cognome TEXT NOT NULL,"
                + "eta INTEGER NOT NULL,"
                + "IdCredenziali INTEGER NOT NULL,"
                + "FOREIGN KEY(IdCredenziali) REFERENCES tblCredenziali(ID),"
                + "UNIQUE(Nome,Cognome),"
                + "CHECK (eta BETWEEN 13 AND 25));");

        scriviSuFile("DROP TABLE IF EXISTS tblDocente;");
        scriviSuFile("CREATE TABLE tblDocente("
                + "CodFis TEXT primary key NOT NULL,"
                + "Nome TEXT NOT NULL,"
                + "Cognome TEXT NOT NULL,"
                + "età INTEGER NOT NULL,"
                + "IdCredenziali INTEGER NOT NULL,"
                + "FOREIGN KEY(IdCredenziali) REFERENCES tblCredenziali(ID),"
                + "UNIQUE(Nome,Cognome),"
                + "CHECK (età BETWEEN 25 AND 80));");

        scriviSuFile("DROP TABLE IF EXISTS tblInterrogazione;");
        scriviSuFile("CREATE TABLE tblInterrogazione("
                + "ID INTEGER primary key AUTOINCREMENT NOT NULL,"
                + "Girone INTEGER NOT NULL,"
                + "Matricola TEXT NOT NULL,"
                + "Giorno TEXT NOT NULL,"
                + "FOREIGN KEY(Matricola) REFERENCES tblStudente(Matricola),"
                + "FOREIGN KEY(Giorno) REFERENCES tblGiornata(Giorno));");

        scriviSuFile("DROP TABLE IF EXISTS tblGiornata;");
        scriviSuFile("CREATE TABLE tblGiornata("
                + "Giorno TEXT PRIMARY KEY NOT NULL,"
                + "Posti INTEGER NOT NULL,"
                + "CHECK (Posti BETWEEN 1 AND 3));");
        scriviSuFile("DROP TABLE IF EXISTS tblCredenziali;");
        scriviSuFile("CREATE TABLE tblCredenziali("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "Username TEXT NOT NULL UNIQUE,"
                + "Password TEXT NOT NULL);");
    }

    private void scriviSuFile(String s) {
        try {
            buff.write(s);
            buff.newLine();
            buff.flush();
        } catch (IOException ex) {
            Logger.getLogger(CreazioneDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
