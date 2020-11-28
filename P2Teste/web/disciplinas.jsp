<%--
    Document   : disciplinas
    Created on : 29 de set de 2020, 20:01:59
    Author     : Luciano

--%>

<%@page import="java.util.*"%>
<%@page import=" br.disciplina.fatec.poo.Disciplina"%>
<%@page import=" web.DbListener"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/jspf/menu.jspf" %>


<%
    String errorMessage = null;
    if(request.getParameter("insert")!=null){
        try{
            String name = request.getParameter("name");
            DbListener.insert(name);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            errorMessage = "Erro ao inserir tarefa: "
                    +ex.getMessage();
        }
    }
    if(request.getParameter("delete")!=null){
        try{
            String name = request.getParameter("name");
            DbListener.delete(name);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            errorMessage = "Erro ao excluir tarefa: "
                    +ex.getMessage();
        }
    }
    ArrayList<String> tasks = new ArrayList<>();
    try{
        tasks = DbListener.select();
    }catch(Exception ex){
        errorMessage = "Erro ao ler tarefas: "
                +ex.getMessage();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Avaliação P2 POO</title>
    </head>
    <body>
        <h1>Análise e Des. de Sistemas</h1>
        <h2>Disciplinas</h2>
        <form>
            <input type="text" name="name"/>
            <input type="submit" name="insert" value="Inserir nova Disciplina"/>
        </form>
        <hr/>
        <%if(errorMessage!=null){%>
            <div style="color: red"><%= errorMessage %></div>
            <hr/>
        <%}%>
        <table border="1">
            <tr>
                <th>Disciplina</th>
                <th>Editar</th>
            </tr>
            <%for(String name: tasks){%>
            <tr>
                <td><%= name %></td>
                <td>
                    <form>
                        <input type="hidden" name="name" value="<%= name %>"/>
                        <input type="submit" name="delete" value="Excluir"/>
                    </form>
                </td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
