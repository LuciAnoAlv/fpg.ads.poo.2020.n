<%-- 
    Document   : transacoes
    Created on : 24 de nov de 2020, 08:32:20
    Author     : Luciano
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Transacao"%>
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
            String datahora = request.getParameter("datahora");
            String descricao = request.getParameter("descricao");
            String categoria = request.getParameter("categoria");
            String origem = request.getParameter("origem");
            double valor = Double.parseDouble(request.getParameter("valor"));
            Transacao.insert(datahora, descricao, categoria, origem, valor);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            exceptionMessage = ex.getLocalizedMessage();
        }
    }
    if(request.getParameter("FormUpdate")!=null){
        try{
            long rowid = Long.parseLong(request.getParameter("rowid"));
            String datahora = request.getParameter("datahora");
            String descricao = request.getParameter("descricao");
            String categoria = request.getParameter("categoria");
            String origem = request.getParameter("origem");
            double valor = Double.parseDouble(request.getParameter("valor"));
            Transacao.update(rowid, datahora, descricao, categoria, origem, valor);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            exceptionMessage = ex.getLocalizedMessage();
        }
    }
    if(request.getParameter("FormDelete")!=null){
        try{
            long rowid = Long.parseLong(request.getParameter("rowid"));
            Transacao.delete(rowid);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            exceptionMessage = ex.getLocalizedMessage();
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transações - MyFinance</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <h2>Transações</h2>
        <%if(session.getAttribute("session.login") == null){%>
            <div>Você não tem permissão para acessar esta página</div>
        <%}else{%>
            <%if(exceptionMessage!=null){%>
                <div style="color: red"><%= exceptionMessage %></div>
            <%}%>
            <%if(request.getParameter("prepareInsert")!=null){%>
                <h3>Inserir registro</h3>
                <form>
                    Data/hora: <input type="datetime-local" name="datahora" 
                                value="<%= new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").format(new Date()) %>"/>
                    Descrição: <input type="text" name="descricao"/>
                    Categoria: <select name="categoria">
                        <option value=""></option>
                        <%for(Categoria c: Categoria.getList()){%>
                            <option value="<%= c.getNome() %>"><%= c.getNome() %></option>
                        <%}%>
                    </select>
                    Origem: <input type="text" name="origem"/>
                    Valor: <input type="number" step="0.01" name="valor"/>
                    <input type="submit" name="FormInsert" value="Inserir"/>
                    <input type="submit" name="Cancelar" value="Cancelar"/>
                </form>
            <%}else if(request.getParameter("preprarUpdate")!=null){%>
                <h3>Alterar registro</h3>
                <form>
                    <%
                        String rowid = request.getParameter("rowid");
                        String datahora = request.getParameter("datahora");
                        String descricao = request.getParameter("descricao");
                        String categoria = request.getParameter("categoria");
                        String origem = request.getParameter("origem");
                        String valor = request.getParameter("valor");
                    %>
                    <input type="hidden" name="rowid" value="<%= rowid %>"/>
                    Data/hora: <input type="datetime-local" name="datahora" 
                                value="<%= datahora %>"/>
                    Descrição: <input type="text" name="descricao" value="<%= descricao %>"/>
                    Categoria: <select name="categoria">
                        <%for(Categoria c: Categoria.getList()){%>
                            <option value="<%= c.getNome() %>" <%= c.getNome().equals(categoria) ? "selected": "" %>>
                                <%= c.getNome() %>
                            </option>
                        <%}%>
                    </select>
                    Origem: <input type="text" name="origem" value="<%= origem %>"/>
                    Valor: <input type="number" step="0.01" name="valor" value="<%= valor %>"/>
                    <input type="submit" name="FormUpdate" value="Alterar"/>
                    <input type="submit" name="Cancelar" value="Cancelar"/>
                </form>
            <%}else if(request.getParameter("preprarDelete")!=null){%>
                <h3>Excluir registro</h3>
                <form>
                    <% String rowid = request.getParameter("rowid"); %>
                    <input type="hidden" name="rowid" value="<%= rowid %>"/>
                    Excluir o registro <b><%= rowid %></b>?
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
                    <th>ID</th>
                    <th>Data/hora</th>
                    <th>Descição</th>
                    <th>Categoria</th>
                    <th>Origem</th>
                    <th>Valor</th>
                    <th>Comandos</th>
                </tr>
                <%for(Transacao t: Transacao.getList()){%>
                <tr>
                    <td><%= t.getRowId() %></td>
                    <td><%= t.getDatahora() %></td>
                    <td><%= t.getDescricao() %></td>
                    <td><%= t.getCategoria() %></td>
                    <td><%= t.getOrigem()%></td>
                    <td><%= t.getValor()%></td>
                    <td>
                        <form>
                            <input type="hidden" name="rowid" value="<%= t.getRowId() %>"/>
                            <input type="hidden" name="datahora" value="<%= t.getDatahora() %>"/>
                            <input type="hidden" name="descricao" value="<%= t.getDescricao() %>"/>
                            <input type="hidden" name="categoria" value="<%= t.getCategoria() %>"/>
                            <input type="hidden" name="origem" value="<%= t.getOrigem() %>"/>
                            <input type="hidden" name="valor" value="<%= t.getValor() %>"/>
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
