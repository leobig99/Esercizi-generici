/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelloMorraCinese;

import java.util.Random;

/**

 @author andrea
 */
public class MorraCineseDifficile {
	public static final int CARTA = 0;
	public static final int SASSO = 1;
	public static final int FORBICI = 2;
	public int puntiG = 0;
	public int puntiPC = 0;
	public int mossaPC;
	public int contaCarta;
	public int contaForbici;
	public int contaSasso;
	public int mossaPrec;


	private int mossaComputer() {
		Random gen = new Random();
		mossaPC = gen.nextInt(11);
		return mossaPC;
	}

	public String mossaGiocatore(int i) {
		String s = "";
		int app = mossaComputer();
		switch (i) {
			case CARTA:
				if (app == 1) {
					contaCarta++;
					return s = "Pareggio!";

				}
				if (app == 2) {
					puntiG++;
					contaCarta++;
					return s = "Hai vinto!";
				}
				else{
					puntiPC++;
					contaCarta++;
					return s = "Hai perso!";
				}
			case SASSO:
				if (app == 1) {
					puntiPC++;
					contaSasso++;
					return s = "Hai perso!";
				}
				if (app == 2) {
					contaSasso++;
					return s = "Pareggio!";
				}
				else{
					contaSasso++;
					puntiG++;
					return s = "Hai vinto!";
				}
			case FORBICI:
				if (app == 1) {
					contaForbici++;
					puntiG++;
					return s = "Hai vinto!";
				}
				if (app == 2) {
					contaForbici++;
					puntiPC++;
					return s = "Hai perso!";
				}else{
					contaForbici++;
					return s = "Pareggio!";
				}
		}
		return s;
	}

	public void reset() {
		this.puntiG = 0;
		this.puntiPC = 0;
	}

}
