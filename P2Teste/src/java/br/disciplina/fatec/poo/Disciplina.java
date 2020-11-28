/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package br.disciplina.fatec.poo;
import java.sql.*;
import java.util.*;
import  web.DbListener;
/**
 *
 * @author Luciano
 */
public class Disciplina {
    
    private String nome;
    private String ementa;
    private int ciclo;
    private Double nota;
    private static List<String> lista = new ArrayList();

    
    
    public Disciplina() {
        
    }
    
    public Disciplina(String nome, String ementa, int ciclo) {
        this.nome = nome;
        this.ementa = ementa;
        this.ciclo = ciclo;
        
    }
    
    public Disciplina(String nome) {
        this.nome = nome;
        
    }
    
    public static ArrayList<Disciplina> getList() throws Exception{
        ArrayList<Disciplina> list = new ArrayList<>();
        Connection con = null; Statement stmt = null; ResultSet rs = null;
        Exception methodException = null;
        try{
            con = DbListener.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM disciplina");
            while(rs.next()){
                list.add(new Disciplina(rs.getString("nome")));
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

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public static String getCreateStatement(){
        return "CREATE TABLE IF NOT EXISTS disciplina("
                + "nome VARCHAR(50) UNIQUE NOT NULL,"
                + ")";
    }

    
    }
