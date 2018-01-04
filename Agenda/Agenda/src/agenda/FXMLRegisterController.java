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

/**
 * FXML Controller class
 *
 * @author andrea
 */
public class FXMLRegisterController implements Initializable {

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

    private Agenda agenda;

    @FXML
    public void register() throws Exception {
        System.out.println(mail.getText());
        String n = nome.getText();
        String co = cognome.getText();
        String use = username.getText();
        String pass = password.getText();
        if (n.equals("") || co.equals("") || use.equals("") || pass.equals("")) {
            label.setText("Campi mancanti - Registrazione non effettuata");
            System.out.println("Campi mancanti - Registrazione non effettuata");
        } else {
            Persona p = new Persona(nome.getText(), cognome.getText(), mail.getText(), username.getText(), password.getText());
            OperazioniDB opDB = new OperazioniDB();
            if (opDB.registraPersona(p)) {
                label.setText("Registrazione completata");
                agenda.start(Agenda.login);
                agenda.registerClose();
                System.out.println("Registrazione completata");
            } else {
                System.out.println("Utente già esistente");
                label.setText("Utente già esistente");
            }
        }
    }

    @FXML
    public void Return() throws Exception {
        agenda.start(Agenda.login);
        agenda.registerClose();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agenda = new Agenda();
    }

}
