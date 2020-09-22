<%-- 
    Document   : index
    Created on : 21 de set de 2020, 19:20:13
    Author     : Luciano
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="br.manu.clientes.Users"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%
     ArrayList<Users> userList = (ArrayList) application.getAttribute("userList");
     if(userList == null){
         userList = new ArrayList();
         userList.add(new Users("Administrador", "35991213", "admin@domain.com"));
         userList.add(new Users("Fulano", "33577477", "fulano@domain.com"));
         userList.add(new Users("Beltrano", "23417787", "beltrano@domain.com"));
         userList.add(new Users("Cicrano","22334477" , "cicrano@domain.com"));
         application.setAttribute("userList", userList);
     }
     
     if(request.getParameter("add")!=null){
         String name = request.getParameter("name");
         String telefone = request.getParameter("telefone");
         String email = request.getParameter("email");
         userList.add(new Users(name, telefone, email));
         response.sendRedirect(request.getRequestURI());
     }
     if(request.getParameter("remove")!=null){
         int i = Integer.parseInt(request.getParameter("i"));
         userList.remove(i);
         response.sendRedirect(request.getRequestURI());
     }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuários - JavaOO</title>
    </head>
    <body>
        <div><a href="index.jsp">Voltar</a></div>
        <h1>Contatos</h1>
        <form method="post">
            Nome: <input type="text" name="name"/>
            Telefone: <input type="text" name="telefone"/>
            Email: <input type="text" name="email"/>
            <input type="submit" name="add" value="Adicionar Contato"/>
        </form>
        <hr/>
        <table border="4">
            <tr>
                <th>Índice</th>
                <th>Nome</th>
                <th>Telefone</th>
                <th>Email</th>
                <th>Remover</th>
            </tr>
            <%for(int i=0; i<userList.size(); i++){%>
                <% Users user = userList.get(i); %>
                <tr>
                    <td><%= i %></td>
                    <td><%= user.getName() %></td>
                    <td><%= user.getTelefone() %></td>
                    <td><%= user.getEmail() %></td>
                    <td>
                        <form>
                            <input type="submit" name="remove" value="Remover Contato"/>
                            <input type="hidden" name="i" value="<%= i %>"/>
                        </form>
                    </td>
                </tr>
            <%}%>
        </table>
    </body>
</html>