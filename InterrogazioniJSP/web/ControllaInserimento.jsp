<%-- 
    Document   : ControllaInserimento
    Created on : 10-feb-2018, 18.23.11
    Author     : andrea
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
                String nome = request.getParameter("nome");
                System.out.println(nome);
                String cognome = request.getParameter("cognome");
                System.out.println(cognome);
                String età = request.getParameter("eta");
                System.out.println(età);
                String username = request.getParameter("username");
                System.out.println(username);
                String password = request.getParameter("password");
                System.out.println(password);
                if (nome.equals("") || cognome.equals("") || età.equals("") || username.equals("") || password.equals("")) {
        %>
        <jsp:forward page="InserisciStudente.jsp" >
            <jsp:param name="error" value="e"/>
        </jsp:forward>
        <%
                } else {
                    OperazioniDB opDB = new OperazioniDB();
                    opDB.insertStudente(nome, cognome, Integer.parseInt(età), username, password);
                    response.sendRedirect("InserisciStudente.jsp");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("ErrorPage.jsp");
            }
        %>
    </body>
</html>
