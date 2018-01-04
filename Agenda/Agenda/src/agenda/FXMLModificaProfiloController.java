/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import database.OperazioniDB;
import entity.Persona;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author andrea
 */
public class FXMLModificaProfiloController implements Initializable {

    private Persona persona;
    private Agenda agenda;
    private OperazioniDB opDB;

    @FXML
    private TextField nome;
    @FXML
    private TextField cognome;
    @FXML
    private TextField mail;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    private Label label;

    @FXML
    public void exit() throws Exception {
        agenda.closeModificaProfilo();
    }

    @FXML
    public void save() {
        String oldUsername = persona.getUsername();
        String oldPassword = persona.getPassword();
        persona.setCognome(cognome.getText());
        persona.setNome(nome.getText());
        persona.setMail(mail.getText());
        persona.setUsername(username.getText());
        persona.setPassword(password.getText());
        if (opDB.modificaPersona(persona, oldUsername, oldPassword)) {
            label.setTextAlignment(TextAlignment.CENTER);
            label.setText("Operazione riuscita");
        } else {
            label.setTextAlignment(TextAlignment.CENTER);
            label.setText("Operazione non riuscita");
        }
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
        System.out.println(persona);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agenda = new Agenda();
        opDB = new OperazioniDB();
    }

}
