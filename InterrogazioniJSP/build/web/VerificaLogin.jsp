<%-- 
    Document   : VerificaLogin
    Created on : 2-feb-2018, 10.35.04
    Author     : andrea.zoccarato
--%>

<%@page import="Controllo.OperazioniDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verifica</title>
    </head>
    <body>
        <%
            try {
                String username = (String) request.getParameter("username");
                String password = (String) request.getParameter("password");
                out.println(username);
                out.println(password);
                OperazioniDB opDb = new OperazioniDB();
                if (opDb.login(username, password) == "Studente") {
        %>
        <jsp:forward page="Studente.jsp"/>
        <%
        } else if (opDb.login(username, password) == "Docente") {
        %>
        <jsp:forward page="Docente.jsp"/>
        <%
        } else {
        %>
        <jsp:forward page="Login.jsp" >
            <jsp:param name="error" value="e"/>
        </jsp:forward>
        <%
            }
        } catch (Exception e) {
        %>
        <jsp:forward page="ErrorPage.jsp"/>
        <%
            }
        %>

    </body>
</html>

