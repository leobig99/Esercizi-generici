package ModelloMorraCinese;

import java.util.Random;

/**
 * @author andrea.zoccarato
 */
public class MorraCineseNormale {

    public static final int CARTA = 0;
    public static final int SASSO = 1;
    public static final int FORBICI = 2;
    public int puntiG = 0;
    public int puntiPC = 0;
    public int mossaPC;

    private int mossaComputer() {
        Random gen = new Random();
        mossaPC = gen.nextInt(3);
        return mossaPC;
    }

    public String mossaGiocatore(int i) {
        String s = "";
        int app = mossaComputer();
        switch (i) {
            case CARTA:
                if (app == CARTA) {
                    return s = "Pareggio!";
                }
                if (app == SASSO) {
                    puntiG++;
                    return s = "Hai vinto!";
                }
                if (app == FORBICI) {
                    puntiPC++;
                    return s = "Hai perso!";
                }
                break;
            case SASSO:
                if (app == CARTA) {
                    puntiPC++;
                    return s = "Hai perso!";
                }
                if (app == SASSO) {
                    return s = "Pareggio!";
                }
                if (app == FORBICI) {
                    puntiG++;
                    return s = "Hai vinto!";
                }
                break;
            case FORBICI:
                if (app == CARTA) {
                    puntiG++;
                    return s = "Hai vinto!";
                }
                if (app == SASSO) {
                    puntiPC++;
                    return s = "Hai perso!";
                }
                if (app == FORBICI) {
                    return s = "Pareggio!";
                }
                break;
        }
        return s;
    }

    public void reset() {
        this.puntiG = 0;
        this.puntiPC = 0;
    }


}
