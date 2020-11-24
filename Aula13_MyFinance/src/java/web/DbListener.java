/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;
import model.Categoria;
import model.Transacao;
import model.Usuario;

/**
 * Web application lifecycle listener.
 *
 * @author rlarg
 */
public class DbListener implements ServletContextListener {
    private static final String CLASS_NAME = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:my-finance.db";
    
    public static String exceptionMessage = null;
    
    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(DB_URL);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String etapa = "Inicialização";
        Connection con = null;
        Statement stmt = null;
        try{
            etapa = "Registro a classe do driver";
            Class.forName(CLASS_NAME);
            etapa = "Conectar com o banco de dados";
            con = getConnection();
            etapa = "Preparando o statement";
            stmt = con.createStatement();
            etapa = "Criar a tabela de usuários";
            stmt.execute(Usuario.getCreateStatement());
            if(Usuario.getList().isEmpty()){
                etapa = "Criar primeiros usuários";
                stmt.execute("INSERT INTO usuarios VALUES ("
                        + "'admin',"
                        + "'Administrador do Sistema',"
                        + "'ADMINISTRADOR',"
                        + "1234".hashCode()
                        + ")");
                stmt.execute("INSERT INTO usuarios VALUES ("
                        + "'Luciano',"
                        + "'Luciano Alves Da Silva',"
                        + "'USUÁRIO',"
                        + "1234".hashCode()
                        + ")");
            }
            etapa = "Criar a tabela de categorias";
            stmt.execute(Categoria.getCreateStatement());
            etapa = "Criar a tabela de transacoes";
            stmt.execute(Transacao.getCreateStatement());
            etapa = "Desconectar do banco de dados";
        }catch(Exception ex){
            exceptionMessage = etapa+": "+ex.getLocalizedMessage();
        }finally{
            try{stmt.close();}catch(Exception ex2){}
            try{con.close();}catch(Exception ex2){}
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}