<%-- 
    Document   : Docente
    Created on : 2-feb-2018, 11.02.49
    Author     : andrea.zoccarato
--%>

<%@page import="Controllo.OperazioniDB"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1>Docente</h1>
        <div>
            <form action="VerificaStudente.jsp" method="post">
                <input type="date" name="data">
                <select name="Studenti">
                    <%
                        //guarda come passare il parametro
                        OperazioniDB opDB = new OperazioniDB();
                        ArrayList<String> studenti = opDB.getStudenti();
                        out.println("<option disabled selected value> -- seleziona uno studente -- </option>");
                        for (int i = 1; i < studenti.size(); i++) {
                            out.println("<option value=\"" + studenti.get(i) + "\">" + studenti.get(i) + "</option>");
                        }
                    %>	
                </select>
                <br><br>
                <input type="submit" value="Set Interrogazione" />
            </form>
        </div>
        <br><br><br><br>
        <div>
            <form action="MostraGironi.jsp" method="post">
                <select name="Gironi">
                    <%
                        request.setAttribute("Ruolo", "Docente");
                        int gironi = opDB.getGirone();
                        out.println("<option disabled selected value> -- seleziona un girone -- </option>");
                        for (int i = 1; i < gironi; i++) {
                            out.println("<option value=\"" + i + "\">" + "Girone: " + i + "</option>");
                        }
                    %>
                </select>
                <input type="submit" value="mostra gironi" />
            </form>
        </div>
        <br><br><br><br>
        <div>
            <form action="InserisciStudente.jsp" method="post">
                <input type="submit" value="InserisciStudente" />
            </form>
        </div>        
        <%
            try {
                String result = (String) session.getAttribute("risultato");
                if (result != null) {
                    out.println("<p style='color:green;'>" + result + "</p>");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("Login.jsp");
            }
        %>
    </center>
</body>
</html>
