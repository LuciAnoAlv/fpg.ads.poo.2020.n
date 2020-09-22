<%-- 
    Document   : index
    Created on : 21 de set de 2020, 19:20:13
    Author     : Luciano
--%>

<%@page import="br.edu.fatecpg.poo.Data"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Data hoje = new Data(21, 9, 2020);
    Data nasc = new Data();
    nasc.setDia(1);
    nasc.setMês(7);
    nasc.setAno(1979);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index - JavaOO</title>
    </head>
    <body>
        <div><a href="index.jsp">Voltar</a></div>
        <h1>JavaOO</h1>
        <h2>Classe Data</h2>
        <h3>Hoje:</h3>
        <div>
            <%= hoje.getData() %>
        </div>
        <h3>Meu nascimento:</h3>
        <div>
            <%= nasc.getDia() %>/<%= nasc.getMês() %>/<%= nasc.getAno() %>
        </div>
    </body>
</html>