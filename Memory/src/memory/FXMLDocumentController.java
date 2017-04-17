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
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private int pos1;
    private int pos2;
    private Stack<ImageView> s;
    private int mosse = 0;

    private boolean premuto[];

    private boolean controllo() {
        return mossa1 == mossa2;
    }

    @FXML
    protected void mossauno(ActionEvent event) throws InterruptedException {
        if (!premuto[0]) {
            premuto[0] = true;
            scopri(1);
            this.s.push(uno);
            if (conta == 0) {
                mossa1 = campo[0];
                pos1 = 0;
                conta++;
                mosse++;
                nMosse.setText("" + mosse);
            } else if (conta == 1) {
                mossa2 = campo[0];
                pos2 = 0;
                conta++;
                if (controllo()) {
                    s.removeAllElements();
                } else {
                    while (!s.empty()) {
                        s.pop().setImage(this.retro);
                    }
                    premuto[pos1] = false;
                    premuto[pos2] = false;
                }
                mosse++;
                conta = 0;
                nMosse.setText("" + mosse);
            }
        }

    }

    @FXML
    protected void mossadue(ActionEvent event) throws InterruptedException {
        if (!premuto[1]) {
            premuto[1] = true;
            scopri(2);
            this.s.push(due);
            if (conta == 0) {
                mossa1 = campo[1];
                pos1 = 1;
                conta++;
                mosse++;
                nMosse.setText("" + mosse);
            } else if (conta == 1) {
                mossa2 = campo[1];
                pos2 = 1;
                conta++;
                if (controllo()) {
                    s.removeAllElements();
//                    setIm(m.getpos(mossa1), empty);
                } else {
//                    TimeUnit.SECONDS.sleep(1);

                    while (!s.empty()) {
                    }
                    premuto[pos1] = false;
                    premuto[pos2] = false;
                }
                mosse++;
                conta = 0;
                nMosse.setText("" + mosse);
            }
        }
    }

    @FXML
    protected void mossatre(ActionEvent event) throws InterruptedException {
        if (!premuto[2]) {
            premuto[2] = true;
            scopri(3);
            this.s.push(tre);
            if (conta == 0) {
                mossa1 = campo[2];
                pos1 = 2;
                conta++;
                mosse++;
                nMosse.setText("" + mosse);
            } else if (conta == 1) {
                mossa2 = campo[2];
                pos2 = 2;
                conta++;
                if (controllo()) {
                    s.removeAllElements();
                } else {
//                    TimeUnit.SECONDS.sleep(1);

                    while (!s.empty()) {
                        s.pop().setImage(this.retro);
                    }
                    premuto[pos1] = false;
                    premuto[pos2] = false;
                }
                mosse++;
                conta = 0;
                nMosse.setText("" + mosse);
            }
        }
    }

    @FXML
    protected void mossaquatto(ActionEvent event) throws InterruptedException {
        if (!premuto[3]) {
            premuto[3] = true;
            scopri(4);
            this.s.push(quattro);
            if (conta == 0) {
                mossa1 = campo[3];
                pos1 = 3;
                conta++;
                mosse++;
                nMosse.setText("" + mosse);
            } else if (conta == 1) {
                mossa2 = campo[3];
                pos2 = 3;
                conta++;
                if (controllo()) {
                    s.removeAllElements();
                } else {
//                    TimeUnit.SECONDS.sleep(1);

                    while (!s.empty()) {
                        s.pop().setImage(this.retro);
                    }
                    premuto[pos1] = false;
                    premuto[pos2] = false;
                }
                mosse++;
                conta = 0;
                nMosse.setText("" + mosse);
            }
        }
    }

    @FXML
    protected void mossacinque(ActionEvent event) throws InterruptedException {
        if (!premuto[4]) {
            premuto[4] = true;
            scopri(5);
            this.s.push(cinque);
            if (conta == 0) {
                mossa1 = campo[4];
                pos1 = 4;
                conta++;
                mosse++;
                nMosse.setText("" + mosse);
            } else if (conta == 1) {
                mossa2 = campo[4];
                pos2 = 4;
                conta++;
                if (controllo()) {
                    s.removeAllElements();
                } else {
//                    TimeUnit.SECONDS.sleep(1);

                    while (!s.empty()) {
                        s.pop().setImage(this.retro);
                    }
                    premuto[pos1] = false;
                    premuto[pos2] = false;
                }
                mosse++;
                conta = 0;
                nMosse.setText("" + mosse);
            }
        }
    }

    @FXML
    protected void mossasei(ActionEvent event) throws InterruptedException {
        if (!premuto[5]) {
            premuto[5] = true;
            scopri(6);
            this.s.push(sei);
            if (conta == 0) {
                mossa1 = campo[5];
                pos1 = 5;
                conta++;
                mosse++;
                nMosse.setText("" + mosse);
            } else if (conta == 1) {
                mossa2 = campo[5];
                pos2 = 5;
                conta++;
                if (controllo()) {
                    s.removeAllElements();
                } else {
//                    TimeUnit.SECONDS.sleep(1);

                    while (!s.empty()) {
                        s.pop().setImage(this.retro);
                    }
                    premuto[pos1] = false;
                    premuto[pos2] = false;
                }
                mosse++;
                conta = 0;
                nMosse.setText("" + mosse);
            }
        }
    }

    @FXML
    protected void mossasette(ActionEvent event) throws InterruptedException {
        if (!premuto[6]) {
            premuto[6] = true;
            scopri(7);
            this.s.push(sette);
            if (conta == 0) {
                mossa1 = campo[6];
                pos1 = 6;
                conta++;
                mosse++;
                nMosse.setText("" + mosse);
            } else if (conta == 1) {
                mossa2 = campo[6];
                pos2 = 6;
                conta++;
                if (controllo()) {
                    s.removeAllElements();
                } else {
//                    TimeUnit.SECONDS.sleep(1);

                    while (!s.empty()) {
                        s.pop().setImage(this.retro);
                    }
                    premuto[pos1] = false;
                    premuto[pos2] = false;
                }
                mosse++;
                conta = 0;
                nMosse.setText("" + mosse);
            }
        }
    }

    @FXML
    protected void mossaotto(ActionEvent event) throws InterruptedException {
        if (!premuto[7]) {
            premuto[7] = true;
            scopri(8);
            this.s.push(otto);
            if (conta == 0) {
                mossa1 = campo[7];
                pos1 = 7;
                conta++;
                mosse++;
                nMosse.setText("" + mosse);
            } else if (conta == 1) {
                mossa2 = campo[7];
                pos2 = 7;
                conta++;
                if (controllo()) {
                    s.removeAllElements();
                } else {
//                    TimeUnit.SECONDS.sleep(1);

                    while (!s.empty()) {
                        s.pop().setImage(this.retro);
                    }
                    premuto[pos1] = false;
                    premuto[pos2] = false;
                }
                mosse++;
                conta = 0;
                nMosse.setText("" + mosse);
            }
        }
    }

    @FXML
    protected void mossanove(ActionEvent event) throws InterruptedException {
        if (!premuto[8]) {
            premuto[8] = true;
            scopri(9);
            this.s.push(nove);
            if (conta == 0) {
                mossa1 = campo[8];
                pos1 = 8;
                conta++;
                mosse++;
                nMosse.setText("" + mosse);
            } else if (conta == 1) {
                mossa2 = campo[8];
                pos2 = 8;
                conta++;
                if (controllo()) {
                    s.removeAllElements();
                } else {
//                    TimeUnit.SECONDS.sleep(1);

                    while (!s.empty()) {
                        s.pop().setImage(this.retro);
                    }
                    premuto[pos1] = false;
                    premuto[pos2] = false;
                }
                mosse++;
                conta = 0;
                nMosse.setText("" + mosse);
            }
        }
    }

    @FXML
    protected void mossadieci(ActionEvent event) throws InterruptedException {
        if (!premuto[9]) {
            premuto[9] = true;
            scopri(10);
            this.s.push(dieci);
            if (conta == 0) {
                mossa1 = campo[9];
                pos1 = 9;
                conta++;
                mosse++;
                nMosse.setText("" + mosse);
            } else if (conta == 1) {
                mossa2 = campo[9];
                pos2 = 9;
                conta++;
                if (controllo()) {
                    s.removeAllElements();
                } else {
//                    TimeUnit.SECONDS.sleep(1);

                    while (!s.empty()) {
                        s.pop().setImage(this.retro);
                    }
                    premuto[pos1] = false;
                    premuto[pos2] = false;
                }
                mosse++;
                conta = 0;
                nMosse.setText("" + mosse);
            }
        }
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
        nMosse.setText("" + 0);
        mosse = 0;
        this.s = new Stack();
        premuto = new boolean[m.getCampo().length];
        Arrays.fill(premuto, false);
    }

}
