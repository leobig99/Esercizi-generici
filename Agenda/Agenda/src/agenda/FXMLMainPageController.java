/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import database.OperazioniDB;
import entity.Persona;
import entity.Promemoria;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author andrea
 */
public class FXMLMainPageController implements Initializable {

    protected String username;
    protected String password;

    private Agenda agenda;
    private OperazioniDB opDB;
    private Persona persona;

    @FXML
    private Label info;

    @FXML
    public void exit() throws Exception {
        agenda.start(Agenda.login);
        agenda.mainClose();
    }

    @FXML
    public void visualizzaProfilo() throws IOException {
        agenda.vediProfilo(persona);
    }

    @FXML
    public void modificaProfilo() throws IOException {
        agenda.modProfilo(persona);
    }

    @FXML
    public void modRegProApp() throws IOException {
        agenda.registrazioneModifica(persona);
    }

    @FXML
    public void visualizzaPromApp() throws IOException {
        agenda.visualizzaPromApp(persona);
    }

    @FXML
    public void cancellaPromemoria() throws IOException {
        agenda.eliminaPromemoria(persona);
    }

    @FXML
    public void promemoriaQuotidiani() {
        ArrayList<Promemoria> prom=opDB.getPromemoriaScadono();
        if(prom.isEmpty()){
            info.setTextAlignment(TextAlignment.CENTER);
            info.setText("Nessun Promemoria che sta per scadere");
        }else{
            String app="";
            for (int i = 0; i < prom.size(); i++) {
                app+=prom.get(i).toString();
            }
            info.setTextAlignment(TextAlignment.CENTER);
            info.setText(app);
        }
    }

    public void setUsePassword(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("Dentro FXMLMainPageController.java->" + username + " " + password);
        persona=opDB.getPersona(username, password);
        promemoriaQuotidiani();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agenda = new Agenda();
        opDB = new OperazioniDB();
    }

}
