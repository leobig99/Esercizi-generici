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

/**
 * FXML Controller class
 *
 * @author andrea
 */
public class FXMLVisualizzaProfiloController implements Initializable {

    @FXML
    private Label nome;
    @FXML
    private Label cognome;
    @FXML
    private Label mail;
    @FXML
    private Label username;
    @FXML
    private Label password;

    private Agenda agenda;
    private Persona persona;

    @FXML
    public void vedi() {
        nome.setText(persona.getNome());
        cognome.setText(persona.getCognome());
        mail.setText(persona.getMail());
        username.setText(persona.getUsername());
        password.setText(persona.getPassword());

    }

    @FXML
    public void exit() throws Exception {
        agenda.closeVisualizzaProfilo();
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
        System.out.println(persona);
        vedi();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agenda = new Agenda();

    }

}
