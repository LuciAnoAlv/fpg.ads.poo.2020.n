/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.sql.*;
import web.DbListener;


/**
 *
 * @author rlarg
 */
public class Usuario {
    private String login;
    private String nome;
    private String papel;
    
    public static ArrayList<Usuario> getList() throws Exception{
        ArrayList<Usuario> list = new ArrayList<>();
        Connection con = null; Statement stmt = null; ResultSet rs = null;
        Exception methodException = null;
        try{
            con = DbListener.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM usuarios");
            while(rs.next()){
                list.add(new Usuario(
                        rs.getString("login"),
                        rs.getString("nome"),
                        rs.getString("papel")
                ));
            }
        }catch(Exception ex){
            methodException = ex;
        }finally{
            try{rs.close();}catch(Exception ex2){}
            try{stmt.close();}catch(Exception ex2){}
            try{con.close();}catch(Exception ex2){}
        }
        if(methodException!=null) throw methodException;
        return list;
    }
    
    public static Usuario getUser(String login, String password) throws Exception{
        Usuario user = null;
        Connection con = null; PreparedStatement stmt = null; ResultSet rs = null;
        Exception methodException = null;
        try{
            con = DbListener.getConnection();
            stmt = con.prepareStatement("SELECT * FROM usuarios  WHERE login = ? and password_hash = ?");
            stmt.setString(1, login);
            stmt.setLong(2, password.hashCode());
            rs = stmt.executeQuery();
            while(rs.next()){
                user = new Usuario(
                        rs.getString("login"),
                        rs.getString("nome"),
                        rs.getString("papel")
                );
            }
        }catch(Exception ex){
            methodException = ex;
        }finally{
            try{rs.close();}catch(Exception ex2){}
            try{stmt.close();}catch(Exception ex2){}
            try{con.close();}catch(Exception ex2){}
        }
        if(methodException!=null) throw methodException;
        return user;
    }
    
    public static void changePassword(String login, String newPassword) throws Exception{
        Usuario user = null;
        Connection con = null; PreparedStatement stmt = null; ResultSet rs = null;
        Exception methodException = null;
        try{
            con = DbListener.getConnection();
            stmt = con.prepareStatement("UPDATE usuarios SET password_hash = ? WHERE login = ?");
            stmt.setLong(1, newPassword.hashCode());
            stmt.setString(2, login);
            stmt.execute();
        }catch(Exception ex){
            methodException = ex;
        }finally{
            try{rs.close();}catch(Exception ex2){}
            try{stmt.close();}catch(Exception ex2){}
            try{con.close();}catch(Exception ex2){}
        }
        if(methodException!=null) throw methodException;
        
    }
    
    
    

    public Usuario(String login, String nome, String papel) {
        this.login = login;
        this.nome = nome;
        this.papel = papel;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public static String getCreateStatement(){
        return "CREATE TABLE IF NOT EXISTS usuarios("
                + "login VARCHAR(50) UNIQUE NOT NULL,"
                + "nome VARCHAR(200) NOT NULL,"
                + "papel VARCHAR(20) NOT NULL,"
                + "password_hash LONG NOT NULL"
                + ")";
    }
}
