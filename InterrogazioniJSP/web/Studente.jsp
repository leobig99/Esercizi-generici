<%-- 
    Document   : Studente
    Created on : 2-feb-2018, 11.02.58
    Author     : andrea.zoccarato
--%>

<%@page import="Controllo.OperazioniDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1>Registro Studente</h1>
        <div>
            <form action="MostraGironi.jsp" method="post">
                <select name="Gironi">
                    <%
                        OperazioniDB opDB = new OperazioniDB();
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
        <%
            try {
                String error = request.getParameter("error");
                if (error != null) {
                    out.println("<br><br><p style='color:red;'>Girone non valido</p>");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("Login.jsp");
            }
        %>   
    </center>
</body>
</html>
