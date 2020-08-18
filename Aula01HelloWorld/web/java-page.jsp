<%-- 
    Document   : java-page
    Created on : 18 de ago de 2020, 16:36:44
    Author     : Luciano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Java Page - HelloWorld</title>
    </head>
    <body>
        <h1>Hello, World!</h1>
        <h2>Java Page</h2>
        <div>Data/hora no servidor: <%= new java.util.Date() %></div>
        <a href="index.html">Voltar para página inicial</a>
    </body>
</html>
