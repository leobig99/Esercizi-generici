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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author andrea
 */
public class FXMLVisualizzaPromAppController implements Initializable {

    private Agenda agenda;
    private Persona persona;
    private OperazioniDB opDB;

    @FXML
    private ListView prom;
    @FXML
    private ListView app;

    public void setPersona(Persona persona) {
        this.persona = persona;
        visualizzaProm();
        visualizzaApp();
    }

    @FXML
    public void exit() {
        agenda.closeAppProm();
    }

    private void visualizzaProm() {
        ArrayList<Promemoria> p = opDB.visualizzaPromemoria(persona);
        ArrayList<String> s = new ArrayList<>();
        s.add("Nessuno Promemoria");
        if (p.isEmpty()) {
            ObservableList<String> content = FXCollections.observableArrayList(s);
            prom.setItems(content);
        } else {
            ObservableList<Promemoria> content = FXCollections.observableArrayList(p);
            prom.setItems(content);
        }

    }

    private void visualizzaApp() {
        ArrayList<Appuntamento> a = opDB.visualizzaAppuntamenti(persona);
        ArrayList<String> s = new ArrayList<>();
        s.add("Nessuno Appuntamento");
        if (a.isEmpty()) {
            ObservableList<String> content = FXCollections.observableArrayList(s);
            app.setItems(content);
        } else {
            ObservableList<Appuntamento> content = FXCollections.observableArrayList(a);
            app.setItems(content);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agenda = new Agenda();
        opDB = new OperazioniDB();
    }

}
