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
public class Ricevimenti {
	int id;
	Docente prof;
	String data;

	public Ricevimenti(int id, Docente prof, String data) {
		this.id = id;
		this.prof = prof;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Docente getProf() {
		return prof;
	}

	public void setProf(Docente prof) {
		this.prof = prof;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	


}
