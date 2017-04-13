/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morracinese2.pkg0;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import server_client.*;

/**
 * FXML Controller class
 *
 * @author Leonardo
 */
public class FXMLMultyPlayerController implements Initializable {
    MorraCinese20 m =new MorraCinese20();

    @FXML
    protected void hostAction(ActionEvent event) throws IOException, InterruptedException {
        new Host().Link();
        m.startMulty("FXMLGraficaMultyPlayer.fxml");
        m.closeOnline();
    }
    
    @FXML
    protected void clientAction(ActionEvent event) throws IOException, InterruptedException {
//        new client().Link();
        m.startMulty("FXMLGraficaMultyPlayer.fxml");
        m.closeOnline();
        
    }
    
    
    /**
     * Initializes
     * @param url the controller class.
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
