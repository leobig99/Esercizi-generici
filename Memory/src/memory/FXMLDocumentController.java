/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import Memo.memoryGioco;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author andrea
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label nMosse;
    @FXML
    private ImageView uno;
    @FXML
    private ImageView due;
    @FXML
    private ImageView tre;
    @FXML
    private ImageView quattro;
    @FXML
    private ImageView cinque;
    @FXML
    private ImageView sei;
    @FXML
    private ImageView sette;
    @FXML
    private ImageView otto;
    @FXML
    private ImageView nove;
    @FXML
    private ImageView dieci;

    @FXML
    private Image retro;
    @FXML
    private Image rag;
    @FXML
    private Image jar;
    @FXML
    private Image bomba;
    @FXML
    private Image coin;
    @FXML
    private Image shr;
    @FXML
    private Image empty;

    private int campo[];

    private memoryGioco m;
    private int conta;
    private int mossa1;
    private int mossa2;
    private int mosse=0;

    private boolean premuto[];
            
    private boolean controllo() {
        return mossa1==mossa2;
    }

    @FXML
    protected void mossauno(ActionEvent event) {
        if(!premuto[0]){
            premuto[0]=true;
            scopri(1);
            if(conta==0){
            mossa1=campo[0];
            conta++;
            mosse++;
            nMosse.setText(""+mosse);
        }else if(conta==1){
            mossa2=campo[0];
            conta++;
            if(controllo()){
                mosse++;
                uno.setImage(empty);
                setIm(m.getpos(mossa1),empty);
                nMosse.setText(""+mosse);
                conta=0;
            }else{
                mosse++;
                uno.setImage(retro);
                setIm(m.getpos(mossa1),retro);
                premuto[0]=false;
                nMosse.setText(""+mosse);
                conta=0;
            }
        }
        }
        
        
    }

    @FXML
    protected void mossadue(ActionEvent event) {
        if(!premuto[1]){
            premuto[1]=true;
            scopri(2);
        if(conta==0){
            mossa1=campo[1];
            conta++;
            mosse++;
            nMosse.setText(""+mosse);
        }else if(conta==1){
            mossa2=campo[1];
            conta++;
            if(controllo()){
                mosse++;
                due.setImage(empty);
                setIm(m.getpos(mossa1),empty);
                nMosse.setText(""+mosse);
                conta=0;
            }else{
                mosse++;
                due.setImage(retro);
                setIm(m.getpos(mossa1),retro);
                premuto[1]=false;
                nMosse.setText(""+mosse);
                conta=0;
            }
        }
        }
    }

    @FXML
    protected void mossatre(ActionEvent event) {
        scopri(3);
    }

    @FXML
    protected void mossaquatto(ActionEvent event) {
        scopri(4);
    }

    @FXML
    protected void mossacinque(ActionEvent event) {
        scopri(5);
    }

    @FXML
    protected void mossasei(ActionEvent event) {
        scopri(6);
    }

    @FXML
    protected void mossasette(ActionEvent event) {
        scopri(7);
    }

    @FXML
    protected void mossaotto(ActionEvent event) {
        scopri(8);
    }

    @FXML
    protected void mossanove(ActionEvent event) {
        scopri(9);
    }

    @FXML
    protected void mossadieci(ActionEvent event) {
        scopri(10);
    }
    
    @FXML
    protected void reset(ActionEvent event) {
        this.initialize(null, null);
    }

    protected void scopri(int i) {
        if (campo[i - 1] == 1) {
            setIm(i, rag);
        }
        if (campo[i - 1] == 2) {
            setIm(i, jar);
        }
        if (campo[i - 1] == 3) {
            setIm(i, coin);
        }
        if (campo[i - 1] == 4) {
            setIm(i, bomba);
        }
        if (campo[i - 1] == 5) {
            setIm(i, shr);
        }
    }

    private void setIm(int n, Image m) {
        if (n == 1) {
            uno.setImage(m);
        }
        if (n == 2) {
            due.setImage(m);
        }
        if (n == 3) {
            tre.setImage(m);
        }
        if (n == 4) {
            quattro.setImage(m);
        }
        if (n == 5) {
            cinque.setImage(m);
        }
        if (n == 6) {
            sei.setImage(m);
        }
        if (n == 7) {
            sette.setImage(m);
        }
        if (n == 8) {
            otto.setImage(m);
        }
        if (n == 9) {
            nove.setImage(m);
        }
        if (n == 10) {
            dieci.setImage(m);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        retro = new Image("file:../Memory/src/images/BackLeggenda.png");
        rag = new Image("file:../Memory/src/images/rag.gif");
        bomba = new Image("file:../Memory/src/images/bomba.jpg");
        jar = new Image("file:../Memory/src/images/jar.gif");
        coin = new Image("file:../Memory/src/images/coin.gif");
        shr = new Image("file:../Memory/src/images/shr.gif");
        empty = new Image("file:../Memory/src/images/Empty.jpg");
        m = new memoryGioco(5);
        m.inizializza();
        campo = m.getCampo();
        uno.setImage(retro);
        due.setImage(retro);
        tre.setImage(retro);
        quattro.setImage(retro);
        cinque.setImage(retro);
        sei.setImage(retro);
        sette.setImage(retro);
        otto.setImage(retro);
        nove.setImage(retro);
        dieci.setImage(retro);
        mossa1 = mossa2 = conta = 0;
        nMosse.setText(""+0);
        mosse=0;
        premuto=new boolean[m.getCampo().length];
        Arrays.fill(premuto,false);
    }

}
