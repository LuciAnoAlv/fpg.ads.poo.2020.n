<%-- 
    Document   : minhaIdade
    Created on : 31 de ago de 2020, 20:32:35
    Author     : Luciano
--%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
    int anoDoMeuNascimento = 1994;
    int idade = anoAtual - anoDoMeuNascimento;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Minha Idade - JSP</title>
    </head>
    <body>
        <h4><a href="index.jsp">voltar</a></h4>
        <h1>JSP</h1>
        <h2>MINHA IDADE</h2>
        <h3>NOME:</h3>
        <h4>LUCIANO ALVES</h4>
        <h2>IDADE:</h2>
        <h1><%=idade%> anos</h1>
    </body>
</html>
