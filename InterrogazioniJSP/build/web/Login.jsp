<%-- 
    Document   : Login
    Created on : 2-feb-2018, 10.33.13
    Author     : andrea.zoccarato
--%>

<%@page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
    <center>
        <h1>Login</h1>
        <form action="VerificaLogin.jsp" method="post">
            <table border="0">
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" value="" /></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" value="" /></td>
                </tr>
            </table>
            <input type="submit" value="Login">
        </form>

        <%
            try {
                String error = request.getParameter("error");
                if (error != null) {
                    out.println("<br><br><p style='color:red;'>Username e/o Password non corretti</p>");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("Login.jsp");
            }
        %>
    </center>
</body>
</html>

