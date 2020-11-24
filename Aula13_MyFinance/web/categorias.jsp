<%-- 
    Document   : categorias
    Created on : 24 de nov de 2020, 08:31:22
    Author     : Luciano
--%>

<%@page import="model.Categoria"%>
<%@page import="model.Usuario"%>
<%@page import="web.DbListener"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String exceptionMessage = null;
    if(request.getParameter("Cancelar")!=null){
        response.sendRedirect(request.getRequestURI());
    }
    if(request.getParameter("FormInsert")!=null){
        try{
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            Categoria.insert(nome, descricao);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            exceptionMessage = ex.getLocalizedMessage();
        }
    }
    if(request.getParameter("FormUpdate")!=null){
        try{
            String nomeAntigo = request.getParameter("nomeAntigo");
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            Categoria.update(nomeAntigo, nome, descricao);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            exceptionMessage = ex.getLocalizedMessage();
        }
    }
    if(request.getParameter("FormDelete")!=null){
        try{
            String nome = request.getParameter("nome");
            Categoria.delete(nome);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            exceptionMessage = ex.getLocalizedMessage();
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categorias - MyFinance</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <h2>Categorias</h2>
        <%if(session.getAttribute("session.login") == null){%>
            <div>Você não tem permissão para acessar esta página</div>
        <%}else{%>
        <%if(exceptionMessage!=null){%>
            <div style="color: red"><%= exceptionMessage %></div>
        <%}%>
            <%if(request.getParameter("prepareInsert")!=null){%>
                <h3>Inserir registro</h3>
                <form>
                    Nome: <input type="text" name="nome"/>
                    Descrição: <input type="text" name="descricao"/>
                    <input type="submit" name="FormInsert" value="Inserir"/>
                    <input type="submit" name="Cancelar" value="Cancelar"/>
                </form>
            <%}else if(request.getParameter("preprarUpdate")!=null){%>
                <h3>Alterar registro</h3>
                <form>
                    <% String nome = request.getParameter("nome"); %>
                    <% String descricao = request.getParameter("descricao"); %>
                    <input type="hidden" name="nomeAntigo" value="<%= nome %>"/>
                    Nome: <input type="text" name="nome" value="<%= nome %>"/>
                    Descrição: <input type="text" name="descricao" value="<%= descricao %>"/>
                    <input type="submit" name="FormUpdate" value="Alterar"/>
                    <input type="submit" name="Cancelar" value="Cancelar"/>
                </form>
            <%}else if(request.getParameter("preprarDelete")!=null){%>
                <h3>Excluir registro</h3>
                <form>
                    <% String nome = request.getParameter("nome"); %>
                    <input type="hidden" name="nome" value="<%= nome %>"/>
                    Excluir o registro <b><%= nome %></b>?
                    <input type="submit" name="FormDelete" value="Excluir"/>
                    <input type="submit" name="Cancelar" value="Cancelar"/>
                </form>
            <%}else{%>
                <form method="post">
                    <input type="submit" name="prepareInsert" value="Inserir"/>
                </form>
            <%}%>
            <h3>Lista</h3>
            <table border="1">
                <tr>
                    <th>Nome</th>
                    <th>Descição</th>
                    <th>Comandos</th>
                </tr>
                <%for(Categoria c: Categoria.getList()){%>
                <tr>
                    <td><%= c.getNome() %></td>
                    <td><%= c.getDescricao() %></td>
                    <td>
                        <form>
                            <input type="hidden" name="nome" value="<%= c.getNome() %>"/>
                            <input type="hidden" name="descricao" value="<%= c.getDescricao()%>"/>
                            <input type="submit" name="preprarUpdate" value="Alterar"/>
                            <input type="submit" name="preprarDelete" value="Excluir"/>
                        </form>
                    </td>
                </tr>
                <%}%>
            </table>
        <%}%>
    </body>
</html>
