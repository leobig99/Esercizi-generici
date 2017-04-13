/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morracinese2.pkg0;

import ModelloMorraCinese.*;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Leonardo
 */
public class FXMLGraficaMultyPlayerController implements Initializable {

    private MorraCinese20 m;
    private MorraCineseMultyPlayer gioco;
    

    @FXML
    private Label risultato;

    @FXML
    private ImageView sceltaG1;

    @FXML
    private ImageView sceltaG2;

    @FXML
    private Label punteggioGiocatore1;

    @FXML
    private Label punteggioGiocatore2;

    @FXML
    private Label app;

    @FXML
    private Label app1;

    @FXML
    Image imCarta;

    @FXML
    Image imSasso;

    @FXML
    Image imForbice;

    @FXML
    Image imVuota;

    public String nome = "";

    @FXML
    protected void MossaCarta(ActionEvent event) {
        gioco.setMossaG1(0);
        new Schermata(this).start();    
    }

    @FXML
    protected void MossaSasso(ActionEvent event) {
        gioco.setMossaG1(1);
        new Schermata(this).start();
    }

    @FXML
    protected void MossaForbici(ActionEvent event) {
        gioco.setMossaG1(2);
        new Schermata(this).start();

    }
    
    public void cambiaSchermo(int puntiG1,int puntiG2,int mossaG1,int mossaG2 ){
            //risultato.setText();
            punteggioGiocatore2.setText("" +puntiG2 );
            punteggioGiocatore1.setText(nome + " " + puntiG1);
            
            if (mossaG1 == MorraCineseImpossibile.CARTA) {
                sceltaG1.setImage(imCarta);
            }
            if ( mossaG1== MorraCineseImpossibile.FORBICI) {
                sceltaG1.setImage(imForbice);
            }
            if (mossaG1 == MorraCineseImpossibile.SASSO) {
                sceltaG1.setImage(imSasso);
            }
            
            
            
            if (mossaG2 == MorraCineseImpossibile.CARTA) {
                sceltaG2.setImage(imCarta);
            }
            if ( mossaG2== MorraCineseImpossibile.FORBICI) {
                sceltaG2.setImage(imForbice);
            }
            if (mossaG2 == MorraCineseImpossibile.SASSO) {
                sceltaG2.setImage(imSasso);
            }
            app.setText("Scelta " + nome);
            app1.setText("Scelta Computer");
        
    }

    @FXML
    protected void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    protected void Help(ActionEvent event) throws Exception {
        m.Help();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        m = new MorraCinese20();
        gioco = MorraCinese20.m;
        nome = StartFXMLController.s;
        System.out.println("document" + StartFXMLController.s);
        punteggioGiocatore2.setText("" + 0);
        punteggioGiocatore1.setText(nome + " " + 0);
        risultato.setText("Start");
        imForbice = new Image("file:../MorraCinese2.0/src/morracinesefx/images/forbici.png");
        imCarta = new Image("file:../MorraCinese2.0/src/morracinesefx/images/carta.png");
        imSasso = new Image("file:../MorraCinese2.0/src/morracinesefx/images/sasso.png");
        imVuota = new Image("file:../MorraCinese2.0/src/morracinesefx/images/vuota.jpg");
    }

}






class Schermata extends Thread implements  Observer {

    
    private int ris1, ris2;
    private boolean Changed = false;
    private MorraCineseMultyPlayer gioco = MorraCinese20.m;
    private FXMLGraficaMultyPlayerController c; 

    public Schermata(FXMLGraficaMultyPlayerController c) {
        this.c = c;
    }
    
    
    
    @Override
    public void run() {
        while(!this.Changed);
        c.cambiaSchermo(ris1, ris2, gioco.getMossaG1(), gioco.getMossaG2());
        

    }

    @Override
    public void update(Observable o, Object arg) {
        ris1 = gioco.getPuntiG1();
        ris2 = gioco.getMossaG2();
        this.Changed = true;
    }

}
