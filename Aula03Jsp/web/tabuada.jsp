<%-- 
    Document   : tabuada
    Created on : 31 de ago de 2020, 20:54:24
    Author     : Luciano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h4><a href="index.jsp">voltar</a></h4>
        <h1>tabuada</h1>
        <%
            double n1 = Double.parseDouble(request.getParameter("n1"));
            for (int i = 0; i <= 10; i++) {
                out.println(n1+" vezes "+i+" é igual a "+n1*i+"/");
            }
        %>
    </body>
</html>
