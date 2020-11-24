<%-- 
    Document   : index
    Created on : 9 de nov de 2020, 20:19:53
    Author     : Luciano
--%>
<%@page import="model.Transacao"%>
<%@page import="model.Categoria"%>
<%@page import="model.Usuario"%>
<%@page import="web.DbListener"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Início - MyFinance</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <h2>Página inicial</h2>
        <h3>Categorias</h3>
        <div><%= Categoria.getList().size() %> registros.</div>
        <h3>Transações</h3>
        <div><%= Transacao.getList().size() %> registros.</div>
        <hr/>
        <div><%= Usuario.getList().size() %> usuários cadastrados.</div>
    </body>
</html>
