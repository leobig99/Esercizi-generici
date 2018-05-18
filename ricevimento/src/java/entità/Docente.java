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
public class Docente {
	
	String nome,cognome,giornoSett,ora;
	int id;

	public Docente( int id, String nome, String cognome, String giornoSett, String ora) {
		this.nome = nome;
		this.cognome = cognome;
		this.giornoSett = giornoSett;
		this.ora = ora;
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognomem(String cognome) {
		this.cognome = cognome;
	}

	public String getGiornoSett() {
		return giornoSett;
	}

	public void setGiornoSett(String giornoSett) {
		this.giornoSett = giornoSett;
	}

	public String getOra() {
		return ora;
	}

	public void setOra(String ora) {
		this.ora = ora;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

	
}
