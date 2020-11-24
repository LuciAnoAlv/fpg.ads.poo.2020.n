/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import web.DbListener;

/**
 *
 * @author Luciano
 */
public class Categoria {
    private String nome;
    private String descricao;
    
    public static ArrayList<Categoria> getList() throws Exception{
        ArrayList<Categoria> list = new ArrayList<>();
        Connection con = null; Statement stmt = null; ResultSet rs = null;
        Exception methodException = null;
        try{
            con = DbListener.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM categorias");
            while(rs.next()){
                list.add(new Categoria(
                        rs.getString("nome"),
                        rs.getString("descricao")
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
    
    public static void insert(String nome, String descricao) throws Exception{
        Connection con = null; PreparedStatement stmt = null; ResultSet rs = null;
        Exception methodException = null;
        try{
            con = DbListener.getConnection();
            stmt = con.prepareStatement("INSERT INTO categorias(nome, descricao) values(?,?)");
            stmt.setString(1, nome);
            stmt.setString(2, descricao);
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
    
    public static void update(String nomeAntigo, String nome, String descricao) throws Exception{
        Connection con = null; PreparedStatement stmt = null; ResultSet rs = null;
        Exception methodException = null;
        try{
            con = DbListener.getConnection();
            stmt = con.prepareStatement("UPDATE categorias SET nome=?, descricao=? WHERE nome=?");
            stmt.setString(1, nome);
            stmt.setString(2, descricao);
            stmt.setString(3, nomeAntigo);
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
    
    public static void delete(String nome) throws Exception{
        Connection con = null; PreparedStatement stmt = null; ResultSet rs = null;
        Exception methodException = null;
        try{
            con = DbListener.getConnection();
            stmt = con.prepareStatement("DELETE FROM categorias WHERE nome=?");
            stmt.setString(1, nome);
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

    public Categoria(String name, String description) {
        this.nome = name;
        this.descricao = description;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public static String getCreateStatement(){
        return "CREATE TABLE IF NOT EXISTS categorias("
                + "nome VARCHAR(50) PRIMARY KEY,"
                + "descricao VARCHAR(200) NOT NULL"
                + ")";
    }
}
