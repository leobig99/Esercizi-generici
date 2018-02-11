<%-- 
    Document   : InserisciStudente
    Created on : 10-feb-2018, 17.43.53
    Author     : andrea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1>Inserimento Studente</h1>
        <div>
            <form action="ControllaInserimento.jsp" method="post">
                <table>
                    <tr>
                        <td>Nome</td>
                        <td><input type="text" name="nome" value="" /></td>
                    </tr>
                    <tr>
                        <td>Cognome</td>
                        <td><input type="text" name="cognome" value="" /></td>
                    </tr>
                    <tr>
                        <td>Età</td>
                        <td><input type="text" name="eta" value="" /></td>
                    </tr>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="username" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="text" name="password" value="" /></td>
                    </tr>
                </table>
                <input type="submit" value="Ok" />
            </form>
        </div>
        <%
            try {
                String error = request.getParameter("error");
                if (error != null) {
                    out.println("<br><br><p style='color:red;'>Tutti i campi sono necessari</p>");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("Login.jsp");
            }
        %>
    </center>  
</body>
</html>
