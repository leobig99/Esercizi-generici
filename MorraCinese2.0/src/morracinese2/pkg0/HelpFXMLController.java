/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morracinese2.pkg0;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author andrea
 */
public class HelpFXMLController implements Initializable {

    private MorraCinese20 m;
    
    @FXML
    protected void Return(ActionEvent event){
        m.closeHelp();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        m=new MorraCinese20();
    }    
    
}
