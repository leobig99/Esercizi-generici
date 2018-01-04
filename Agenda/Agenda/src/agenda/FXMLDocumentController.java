/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import database.OperazioniDB;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author andrea.zoccarato
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    private Label label;

    private Agenda agenda;

    @FXML
    public void login() throws IOException {
        String use = username.getText();
        String pass = password.getText();
        if (use.length() == 0 || pass.length() == 0) {
            label.setText("Username e/o Password errati");
            System.out.println("Username e/o Password errati");
        } else {
            OperazioniDB opDB = new OperazioniDB();
            if (opDB.login(use, pass)) {
                agenda.closeLogin();
                agenda.mainPage(use, pass);
                label.setText("Login Accepted");
                System.out.println("Login accepted");
            } else {
                label.setTextAlignment(TextAlignment.CENTER);
                label.setText("Username e/o Password errati");
                System.out.println("Username e/o Password errati");
            }
        }
    }

    @FXML
    public void register() throws IOException {
        agenda.register();
        agenda.closeLogin();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agenda = new Agenda();
    }

}
