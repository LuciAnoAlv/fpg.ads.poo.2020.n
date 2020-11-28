/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;


import java.sql.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import br.disciplina.fatec.poo.Disciplina;
import java.util.ArrayList;

/**
 * Web application lifecycle listener.
 *
 * @author Luciano
 */
public class DbListener implements ServletContextListener {
    
    private static final String CLASS_NAME = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:my-finance.db";
    
    public static String exceptionMessage = null;
    
    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(URL);

    }
    
    public static ArrayList<String> select() throws Exception{
        ArrayList<String> list = new ArrayList<>();
        Class.forName(CLASS_NAME);
        Connection con = DriverManager.getConnection(URL);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM disciplina");
        while(rs.next()){
            list.add(rs.getString("name"));
        }
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
    
    public static void insert(String name) throws Exception{
        Class.forName(CLASS_NAME);
        Connection con = DriverManager.getConnection(URL);
        Statement stmt = con.createStatement();
        stmt.execute("insert into disciplina values('"+name+"')");
        stmt.close();
        con.close();
    }
    public static void delete(String name) throws Exception{
        Class.forName(CLASS_NAME);
        Connection con = DriverManager.getConnection(URL);
        Statement stmt = con.createStatement();
        stmt.execute("delete from disciplina where name = '"+name+"'");
        stmt.close();
        con.close();
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
            stmt.execute(Disciplina.getCreateStatement());
            if(Disciplina.getList().isEmpty()){
                etapa = "Criar primeiros usuários";
                stmt.execute("INSERT INTO disciplina VALUES ("
                        + "'administração',"
                        + ")");
                stmt.execute("INSERT INTO disciplina VALUES ("
                        + "'Engenharia',"
                        + ")");
            }
            
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
