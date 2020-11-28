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

    public static List<String> getList() {
    
     try {
        lista = DbListener.select();
    
        
        return lista;
     }
     catch (Exception ex) {
         
         return lista;
     }
    }

    
    }
