/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entità;

/**
 *
 * @author leonardo.bigetti
 */
public class Appuntamento {
	Genitore gen;
	Ricevimenti ric;

	public Appuntamento(Genitore gen, Ricevimenti ric) {
		this.gen = gen;
		this.ric = ric;
	}

	public Genitore getGen() {
		return gen;
	}

	public void setGen(Genitore gen) {
		this.gen = gen;
	}

	public Ricevimenti getRic() {
		return ric;
	}

	public void setRic(Ricevimenti ric) {
		this.ric = ric;
	}
	
	
}
