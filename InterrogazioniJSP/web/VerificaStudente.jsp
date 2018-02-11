<%-- 
    Document   : VerificaStudente
    Created on : 9-feb-2018, 12.22.54
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
        <%
            try {
                String data = request.getParameter("data");
                String nomeStud = request.getParameter("Studenti");
                System.out.println(data + "--" + nomeStud);
                OperazioniDB opDB = new OperazioniDB();
                String result = opDB.setInterrogazione(data, nomeStud);
                session.setAttribute("risultato", result);
                response.sendRedirect("Docente.jsp");
            } catch (Exception ex) {
                ex.printStackTrace();
                response.sendRedirect("ErrorPage.jsp");
            }
        %>
    </body>
</html>
