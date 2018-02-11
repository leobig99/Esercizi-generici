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
public class Seed {

    public BufferedWriter buff;

    public Seed() {
        try {
            buff = new BufferedWriter(new FileWriter("seed.txt"));
        } catch (IOException ex) {
            Logger.getLogger(Seed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void popolamentoAutomatico() {
        scriviSuFile(".open database.db");
        scriviSuFile("INSERT INTO tblStudente VALUES("
                + "'ANDZCC99',"
                + "'Andrea',"
                + "'Zoccarato',"
                + "18,"
                + "1"
                + ");");
        scriviSuFile("INSERT INTO tblStudente VALUES("
                + "'LEOBIGG99',"
                + "'Leonardo',"
                + "'Bigetti',"
                + "14,"
                + "2"
                + ");");
        scriviSuFile("INSERT INTO tblDocente VALUES("
                + "'CSALCSAL',"
                + "'Carlo',"
                + "'Salvagno',"
                + "50,"
                + "3"
                + ");");
        scriviSuFile("INSERT INTO tblGiornata VALUES("
                + "'2018-02-05',"
                + "1"
                + ");");
        scriviSuFile("INSERT INTO tblInterrogazione(Girone,Matricola,Giorno) VALUES("
                + "'1',"
                + "'LEOBIGG99',"
                + "'2018-02-05'"
                + ");");
        scriviSuFile("INSERT INTO tblInterrogazione(Girone,Matricola,Giorno) VALUES("
                + "'1',"
                + "'ANDZCC99',"
                + "'2018-02-05'"
                + ");");
        scriviSuFile("INSERT INTO tblCredenziali(Username,Password) VALUES("
                + "'andrea',"
                + "'andreaz001'"
                + ");");
        scriviSuFile("INSERT INTO tblCredenziali(Username,Password) VALUES("
                + "'leo',"
                + "'leo001'"
                + ");");
        scriviSuFile("INSERT INTO tblCredenziali(Username,Password) VALUES("
                + "'csal',"
                + "'csal001'"
                + ");");
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
