<%-- 
    Document   : index
    Created on : 21 de set de 2020, 19:20:13
    Author     : Luciano
--%>

<%@page import="br.edu.fatecpg.poo.Pessoa"%>
<%@page import="br.edu.fatecpg.poo.Data"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Pessoa ricardo = new Pessoa("Ricardo", new Data(1, 7, 1979));
    ricardo.setPai(new Pessoa("José"));
    ricardo.getPai().setPai(new Pessoa("Ramiro"));
    ricardo.setMãe(new Pessoa("Maria"));
    ricardo.getMãe().setPai(new Pessoa("Paulo"));
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
        <h2>Classe Pessoa</h2>
        <div>
            <%= ricardo.getNome() %> nasceu em <%= ricardo.getNascimento().getData() %>
        </div>
        <div>
            <%= ricardo.getNome() %> tem <%= ricardo.getIdade(2020) %> anos.
        </div>
        <div>
            O pai de <%=ricardo.getNome()%> 
            chama-se <%= ricardo.getPai().getNome() %>
        </div>
        <div>
            A mãe de <%=ricardo.getNome()%> 
            chama-se <%= ricardo.getMãe().getNome() %>
        </div>
        <div>
            O avô paterno de <%=ricardo.getNome()%> 
            chama-se <%= ricardo.getAvôPaterno().getNome() %>
        </div>
        <div>
            O avô materno de <%=ricardo.getNome()%> 
            chama-se <%= ricardo.getAvôMaterno().getNome() %>
        </div>
    </body>
</html>