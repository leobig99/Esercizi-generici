/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entità.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author leonardo.bigetti
 */
public class OperazioniDB {
	
	public ArrayList<Ricevimenti> getRicevimenti() throws SQLException{
		Statement stm = DBConnect.getConnection().createStatement();
		String query="SELECT * FROM ricevimentoDel INNER JOIN professore ON ricevimentoDel.idProfessore = professore.idProfessore";
		ResultSet rs = stm.executeQuery(query);
		ArrayList<Ricevimenti> ricev=new ArrayList<>();
		while (rs.next()) {
			int idRicevimentoDel =rs.getInt("idRicevimentoDel");
			String data=rs.getString("data");
			
			int idProfessore =rs.getInt("idProfessore ");
			String nome=rs.getString("Nome");
			String cognome=rs.getString("Cognome");
			String giornoSett=rs.getString("GiornoSett ");
			String ora=rs.getString("Ora");
			
			ricev.add(new Ricevimenti(idRicevimentoDel,new Docente(idProfessore,nome,cognome,giornoSett,ora),data));
		}
		rs.close();
		return ricev;
	}
	
	
	
	
}
