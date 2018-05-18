<%-- 
    Document   : index
    Created on : 18-mag-2018, 11.30.09
    Author     : leonardo.bigetti
--%>

<%@page import="database.OperazioniDB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entità.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>index</title>
    </head>
    <body>
		<select id="ricevimento" onchange="">
			<%
				OperazioniDB db=new OperazioniDB();
				ArrayList<Ricevimenti> ric=db.getRicevimenti();
				for (Ricevimenti r:ric) {
						out.println("<option id=\"r.getId()\">r.getProf().getNome()+r.getProf().getCognome()</option>");
					}
			%>
			
			<option></option>
		</select>
    </body>
</html>
