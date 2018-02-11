<%-- 
    Document   : MostraGironi
    Created on : 10-feb-2018, 17.42.02
    Author     : andrea
--%>

<%@page import="javafx.util.Pair"%>
<%@page import="java.util.ArrayList"%>
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
        <%
            String girone = (String) request.getParameter("Gironi");
            if (girone == null) {
                String ruolo = (String) request.getParameter("Ruolo");
                System.out.println(ruolo);
                session.setAttribute("risultato", "nessun girone selzionato");
                if (ruolo.equals("Studente")) {
        %>
        <jsp:forward page="Studente.jsp" >
            <jsp:param name="error" value="e"/>
        </jsp:forward>
        <%
        } else {
        %>
        <jsp:forward page="Docente.jsp" >
            <jsp:param name="error" value="e"/>
        </jsp:forward>
        <%
            }

        } else {
        %>
        <h1>Interrogazione Girone: <%=girone%></h1>
        <%
                OperazioniDB opDB = new OperazioniDB();
                ArrayList<String> arr = opDB.getInterrogazioni(Integer.parseInt(girone));
                out.println("<div><ul>");
                for (int i = 0; i < arr.size(); i++) {
                    out.println("<li>" + arr.get(i) + "</li>");
                }
                out.println("</ul></div>");
            }
        %>
    </center>
</body>
</html>
