/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloMorraCinese;

import java.util.Observable;

/**
 *
 * @author Leonardo
 */
public class MorraCineseMultyPlayer extends Observable{

    public static final int CARTA = 0;
    public static final int SASSO = 1;
    public static final int FORBICI = 2;
    public int puntiG1 = 0;
    public int puntiG2 = 0;
    public int mossaG2;
    public int mossaG1;
    public String s="";
    
    public Thread t;
    public Thread FXML;

    public void setFXML(Thread FXML) {
        this.FXML = FXML;
    }
    
    

    public void setT(Thread t) {
        this.t = t;
    }

    public int getPuntiG1() {
        return puntiG1;
    }

    public int getPuntiG2() {
        return puntiG2;
    }

    public int getMossaG2() {
        return mossaG2;
    }

    public int getMossaG1() {
        return mossaG1;
    }

    public String getS() {
        return s;
    }
    

    public void setMossaG2(int mossaG2) {
        this.mossaG2 = mossaG2;
        this.gioca();
    }

    public void setMossaG1(int mossaG1) {
        this.mossaG1 = mossaG1;
         t.notify(); 
    }
    
    
    /**
     * 
     * quetso metodo è utilizzato per fare fare le mosse ai giocatori
     * 
     */
    public void gioca() {
        int app=mossaG2;
        int i=mossaG1;
        switch (i) {
            case CARTA:
                if (app == CARTA) {
                    s = "Pareggio!";
                }
                if (app == SASSO) {
                    puntiG1++;
                    s = "Hai vinto!";
                }
                if (app == FORBICI) {
                    puntiG2++;
                    s = "Hai perso!";
                }
                break;
            case SASSO:
                if (app == CARTA) {
                    puntiG2++;
                    s = "Hai perso!";
                }
                if (app == SASSO) {
                    s = "Pareggio!";
                }
                if (app == FORBICI) {
                    puntiG1++;
                    s = "Hai vinto!";
                }
                break;
            case FORBICI:
                if (app == CARTA) {
                    puntiG1++;
                    s = "Hai vinto!";
                }
                if (app == SASSO) {
                    puntiG2++;
                    s = "Hai perso!";
                }
                if (app == FORBICI) {
                    s = "Pareggio!";
                }
                break;
        }
        
        FXML.notify();
       
        
    }
    
}
