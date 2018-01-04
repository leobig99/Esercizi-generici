/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import database.OperazioniDB;
import entity.Persona;
import java.net.URL;
import java.sql.Time;
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
public class FXMLCancellaPromemoriaController implements Initializable {

    private Agenda agenda;
    private OperazioniDB opDB;
    private Persona persona;

    @FXML
    private Label label;

    @FXML
    private TextField descrizione;
    @FXML
    private TextField data;
    @FXML
    private TextField ora;

    @FXML
    public void exit() throws Exception {
        agenda.closeCancellaPromemoria();
    }

    @FXML
    public void esegui() throws Exception {
        if (opDB.cancellaPromemoria(persona, descrizione.getText(), convertToData(data.getText()), convertTime(ora.getText()))) {
            label.setText("Operazione Avvenuta con successo");
        } else {
            label.setText("Operazione non riuscita");
        }
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
