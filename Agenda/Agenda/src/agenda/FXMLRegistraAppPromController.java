/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import database.OperazioniDB;
import entity.Appuntamento;
import entity.Persona;
import entity.Promemoria;
import static java.lang.String.format;
import static java.lang.String.format;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author andrea
 */
public class FXMLRegistraAppPromController implements Initializable {

    public static final int MOD_MODIFICA = 0;
    public static final int MOD_REGISTRAZIONE = 1;
    private boolean isModifica = true;

    private Agenda agenda;
    private Persona persona;
    private OperazioniDB opDB;

    @FXML
    private Label label;
    @FXML
    private Label modalità;

    @FXML
    private TextField descrizione;
    @FXML
    private TextField Data;
    @FXML
    private TextField Ora;
    @FXML
    private TextField Durata;
    @FXML
    private TextField Preavviso;
    @FXML
    private TextField Ricorrenza;
    @FXML
    private TextField scaduto;
    @FXML
    private TextField numPersone;
    @FXML
    private TextField descrizione_Prom;
    @FXML
    private TextField Data_Prom;
    @FXML
    private TextField Ora_Prom;

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @FXML
    public void eseguiPromemoria() {
        Promemoria p = new Promemoria(descrizione.getText(), convertToData(Data.getText()), convertTime(Ora.getText()), Integer.parseInt(Durata.getText()), convertToData(Preavviso.getText()), convertToData(Ricorrenza.getText()), false, opDB.getIdPersona(persona));
        if (!isModifica) {
            if (opDB.registraPromemoria(persona, p)) {
                label.setText("Promemoria registrato");
            } else {
                label.setText("Promemoria non registrato");
            }
        } else {
            if (opDB.modificaPromemoria(persona, p)) {
                label.setText("Operazione completata");
            } else {
                label.setText("Operazione non completata");
            }
        }

    }

    @FXML
    public void eseguiAppuntamento() {
        Appuntamento a = new Appuntamento(false, Integer.parseInt(numPersone.getText()), opDB.getIdPromemoria(persona, descrizione_Prom.getText(), convertToData(Data_Prom.getText()), convertTime(Ora_Prom.getText())));
        if (!isModifica) {
            if (opDB.registraAppuntamento(persona, a)) {
                label.setText("Appuntamento registrato");
            } else {
                label.setText("Appuntamento non registrato");
            }
        } else {
            if (opDB.modificaAppuntamento(persona, a)) {
                label.setText("Operazione completata");
            } else {
                label.setText("Operazione non completata");
            }
        }
    }

    @FXML
    public void exit() {
        agenda.closeRegMod();
    }

    @FXML
    public void modifica() {
        isModifica = true;
        modalità.setText("Modalità Modifica Attiva");
    }

    @FXML
    public void registra() {
        isModifica = false;
        modalità.setText("Modalità Registrazione Attiva");
    }

    private Date convertToData(String data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date parsed = format.parse(data);
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            return sql;
        } catch (ParseException ex) {
            label.setText("Data non valida");
            Logger.getLogger(FXMLCancellaPromemoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Time convertTime(String time) {
        if (time.length() == 5) {
            time += ":00";
        } else if (time.length() == 0) {
            time = "00:00:00";
        }else if(time.length()==8){
            System.out.println("Ok");
        }
        return java.sql.Time.valueOf(time);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agenda = new Agenda();
        opDB = new OperazioniDB();
    }

}
