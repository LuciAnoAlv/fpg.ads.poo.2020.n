<%--
    Document   : index
    Created on : 29 de set de 2020, 18:26:57
    Author     : Luciano
--%>
<%@page import=" br.disciplina.fatec.poo.Disciplina"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/jspf/menu.jspf" %>




<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Avaliação P2 POO</title>
    </head>
    <body>
        <h1>Avaliação POO</h1>
        <h2>Nome: Luciano Alves Da Silva</h2>
        <h2>RA: 1290481823025</h2>
        <h3>Quantidade de Disciplinas: <%out.print(Disciplina.getList().size());%></h3>
    </body>
    
</html>
