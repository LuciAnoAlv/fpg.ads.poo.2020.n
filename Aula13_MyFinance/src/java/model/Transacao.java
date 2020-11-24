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
public class Transacao {
    private long rowId;
    private String datahora;
    private String descricao;
    private String categoria;
    private String origem;
    private double valor;
    
    public static ArrayList<Transacao> getList() throws Exception{
        ArrayList<Transacao> list = new ArrayList<>();
        Connection con = null; Statement stmt = null; ResultSet rs = null;
        Exception methodException = null;
        try{
            con = DbListener.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT rowid, * FROM transacoes");
            while(rs.next()){
                list.add(new Transacao(
                        rs.getLong("rowid"),
                        rs.getString("datahora"),
                        rs.getString("descricao"),
                        rs.getString("categoria"),
                        rs.getString("origem"),
                        rs.getDouble("valor")
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
    
    public static void insert(String datahora, String descricao, String categoria, String origem, double valor) throws Exception{
        Connection con = null; PreparedStatement stmt = null; ResultSet rs = null;
        Exception methodException = null;
        try{
            con = DbListener.getConnection();
            stmt = con.prepareStatement("INSERT INTO transacoes values(?,?,?,?,?)");
            stmt.setString(1, datahora);
            stmt.setString(2, descricao);
            stmt.setString(3, categoria);
            stmt.setString(4, origem);
            stmt.setDouble(5, valor);
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
    
    public static void update(long rowid, String datahora, String descricao, String categoria, String origem, double valor) throws Exception{
        Connection con = null; PreparedStatement stmt = null; ResultSet rs = null;
        Exception methodException = null;
        try{
            con = DbListener.getConnection();
            stmt = con.prepareStatement("UPDATE transacoes "
                    + "SET datahora=?, descricao=?, categoria=?, origem=?, valor=? "
                    + "WHERE rowid=?");
            stmt.setString(1, datahora);
            stmt.setString(2, descricao);
            stmt.setString(3, categoria);
            stmt.setString(4, origem);
            stmt.setDouble(5, valor);
            stmt.setLong(6, rowid);
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
    
    public static void delete(long rowid) throws Exception{
        Connection con = null; PreparedStatement stmt = null; ResultSet rs = null;
        Exception methodException = null;
        try{
            con = DbListener.getConnection();
            stmt = con.prepareStatement("DELETE FROM transacoes WHERE rowid=?");
            stmt.setLong(1, rowid);
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

    public Transacao(long rowId, String datahora, String descricao, String categoria, String origem, double valor) {
        this.rowId = rowId;
        this.datahora = datahora;
        this.descricao = descricao;
        this.categoria = categoria;
        this.origem = origem;
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }

    public String getDatahora() {
        return datahora;
    }

    public void setDatahora(String datahora) {
        this.datahora = datahora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }
    
    public static String getCreateStatement(){
        return "CREATE TABLE IF NOT EXISTS transacoes("
                + "datahora VARCHAR(25) NOT NULL,"
                + "descricao VARCHAR(200) NOT NULL,"
                + "categoria VARCHAR(50) NOT NULL,"
                + "origem VARCHAR(200) NOT NULL,"
                + "valor numeric(10,2) NOT NULL,"
                + "FOREIGN KEY(categoria) REFERENCES categorias(nome)"
                + ")";
    }
}
