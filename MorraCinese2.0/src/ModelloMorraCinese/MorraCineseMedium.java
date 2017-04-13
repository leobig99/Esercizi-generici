package ModelloMorraCinese;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 @author andrea.zoccarato
 */
public class MorraCineseMedium {
	public static final int CARTA = 0;
	public static final int SASSO = 1;
	public static final int FORBICI = 2;
	public int puntiG = 0;
	public int puntiPC = 0;
	public int mossaPC;
	public int contaCarta = 0;
	public int contaForbici = 0;
	public int contaSasso = 0;
	public int prec;
	public Set<Integer> s;
	public boolean primaMossa = false;

	private int mossaComputer() {
		Random gen = new Random();
		mossaPC = gen.nextInt(30);
		return mossaPC;

	}

	public MorraCineseMedium() {
		s = new HashSet<>();
		s.add(contaCarta);
		s.add(contaForbici);
		s.add(contaSasso);
	}

	private int getMax() {
		int max = 0;
		for (int x : s) {
			if (x != prec) {
				max = x;
				break;
			}
		}

		if (max == contaCarta) {
			return CARTA;
		}
		if (max == contaSasso) {
			return SASSO;
		}
		if (max == contaForbici) {
			return FORBICI;
		}
		return max;
	}

	public String mossaGiocatore(int i) {
		String s = "";
		int app;
		if (!this.primaMossa) {
			app = FORBICI;
			this.primaMossa = true;
		} else {
			app = getMax();
		}
		prec = i;
		System.out.println(app);
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
				} else {
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
				} else {
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
				} else {
					contaForbici++;
					return s = "Pareggio!";
				}
		}
		return s;
	}

	public void reset() {
		this.puntiG = 0;
		this.puntiPC = 0;
		primaMossa = false;
		contaCarta = 0;
		contaForbici = 0;
		contaSasso = 0;
	}
}
