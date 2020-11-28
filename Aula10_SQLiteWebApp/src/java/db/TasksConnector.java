/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import java.util.ArrayList;
import org.apache.naming.java.javaURLContextFactory;

/**
 *
 * @author Luciano
 */
public class TasksConnector {
    private static final String CLASS_NAME = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:C:\\Users\\Luciano\\Desktop\\BancoSQLPOO\\tasks-noturno.db";
    
    public static ArrayList<String> select() throws Exception{
        ArrayList<String> list = new ArrayList<>();
        Class.forName(CLASS_NAME);
        Connection con = DriverManager.getConnection(URL);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Task");
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
        stmt.execute("insert into Task values('"+name+"')");
        stmt.close();
        con.close();
    }
    public static void delete(String name) throws Exception{
        Class.forName(CLASS_NAME);
        Connection con = DriverManager.getConnection(URL);
        Statement stmt = con.createStatement();
        stmt.execute("delete from Task where name = '"+name+"'");
        stmt.close();
        con.close();
    }
}
