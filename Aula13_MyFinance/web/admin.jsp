<%-- 
    Document   : admin
    Created on : 23 de nov de 2020, 20:09:32
    Author     : Luciano
--%>

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
        
        <h2>Administração do sistema</h2>
        <%if(session.getAttribute("session.login") == null){%>
            <div>Você não tem permissão para acessar esta página</div>
        <%}else if(!session.getAttribute("session.papel").equals("ADMINISTRADOR")){%>
            <div>Apenas administradores do sistema possuem permissão para acessar esta página</div>
        <%}else {%>
            <h3>Banco de dados</h3>
            <%if(DbListener.exceptionMessage==null){%>
                <div>Banco de dados criado com sucesso.</div>
            <%}else{%>
                <div style="color:red">Falha na criação da base de dados: <%= DbListener.exceptionMessage %></div>
            <%}%>

            <h3>Usuários</h3>
            <%for(Usuario u: Usuario.getList()){%>
            <div><%= u.getNome() %></div>
            <%}%>
        <%}%>
    </body>
</html>
