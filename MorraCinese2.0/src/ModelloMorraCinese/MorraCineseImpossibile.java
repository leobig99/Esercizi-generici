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
public class MorraCineseImpossibile {
	public static final int CARTA = 0;
	public static final int SASSO = 1;
	public static final int FORBICI = 2;
	public int puntiG = 0;
	public int puntiPC = 0;
	public int mossaPC;
	public int contaCarta;
	public int contaForbici;
	public int contaSasso;

	private int mossaComputer() {
		Random gen = new Random();
		mossaPC = gen.nextInt(30);
		return mossaPC;
	}

	public String mossaGiocatore(int i) {
		String s = "";
		int app = mossaComputer();
		System.out.println(app);
		switch (i) {
			case CARTA:
				if (app == 5) {
					mossaPC = SASSO;
					puntiG++;
					return s = "Hai vinto!";
				}
				if (app == 17) {
					mossaPC = CARTA;
					return s = "Pareggio!";
				} else {
					mossaPC = FORBICI;
					puntiPC++;
					return s = "Hai perso";
				}

			case SASSO:
				if (app == 5) {
					mossaPC = FORBICI;
					puntiG++;
				}
				if (app == 17) {
					mossaPC = SASSO;
					return s = "Pareggio!";
				} else {
					mossaPC = CARTA;
					puntiPC++;
					return s = "Hai perso";
				}
			case FORBICI:
				if (app == 5) {
					mossaPC = CARTA;
					puntiG++;
				}
				if (app == 17) {
					mossaPC = FORBICI;
					return s = "Pareggio!";
				} else {
					mossaPC = SASSO;
					puntiPC++;
					return s = "Hai perso";
				}
		}
		return s;
	}

	public void reset() {
		this.puntiG = 0;
		this.puntiPC = 0;
	}

}
