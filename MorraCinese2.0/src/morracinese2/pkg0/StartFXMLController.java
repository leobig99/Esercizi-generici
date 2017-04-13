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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author andrea.zoccarato
 */
public class StartFXMLController implements Initializable {

    private MorraCinese20 m;

    @FXML
    public TextField nome;

    public static String s;

    @FXML
    protected void Start(ActionEvent event) throws IOException, InterruptedException {
        String str = nome.getText();
        m.setNome(str);
        s = str;
        m.startGioco("FXMLDocument.fxml");
        m.closeStart();
    }

    @FXML
    protected void New(ActionEvent event) throws IOException, InterruptedException, Exception {
        m.ReStart();
    }

    @FXML
    protected void close(ActionEvent event) throws IOException, InterruptedException, Exception {
        System.exit(0);
    }

    @FXML
    protected void Help(ActionEvent event) throws IOException, InterruptedException, Exception {
        m.Help();
    }

    @FXML
    protected void setNormal(ActionEvent event) {
        //TO DO
    }

    @FXML
    protected void setHard(ActionEvent event) {
        //TO DO
    }

    @FXML
    protected void setImpossible(ActionEvent event) {
        //TO DO
    }

    @FXML
    protected void Reset(ActionEvent event) {
        //TO DO
    }

    @FXML
    protected void onEnter(KeyEvent key) throws IOException {
        if (key.getCode().equals(KeyCode.ENTER)) {
            String str = nome.getText();
            m.setNome(str);
            s = str;
            m.startGioco("FXMLDocument.fxml");
            m.closeStart();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        m = new MorraCinese20();
    }

}
