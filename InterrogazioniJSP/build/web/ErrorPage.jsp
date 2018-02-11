<%-- 
    Document   : ErrorPage
    Created on : 2-feb-2018, 11.05.04
    Author     : andrea.zoccarato
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link href="style.css" rel="stylesheet" type="text/css"/>
    <body>
    <center>
        <%@ page isErrorPage = "true" %>
        <p>
            <b>
                Siamo spiacenti, si è verificato un errore
                durante l'esecuzione:
            </b>
        </p>
        <br/>
        <br/>
        <a href="Login.jsp">
            <img src="errore.jpg" >
        </a>
        <br><br><br>
        <a href="Login.jsp">Return to login page</a>
    </center>
</body>
</html>
