<%-- 
    Document   : index
    Created on : 7 de set de 2020, 19:28:02
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
        <%@include file = "header.jsp" %>
        <h1><a href="home.jsp">Home</a></h>
        <h1><a href="amortizacao-constante.jsp">Constante</a></h>
        <h1><a href="amortizacao-americana.jsp">Americana</a></h>
        <h1><a href="tabela-price.jsp">Tabela Price</a></h>
        <%@include file = "rodape.jsp" %>
    </body>
</html>
