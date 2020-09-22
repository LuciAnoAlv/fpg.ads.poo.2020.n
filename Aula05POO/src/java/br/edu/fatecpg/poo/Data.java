/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fatecpg.poo;

/**
 * A classe Data serve para manipular uma data de forma simples e didática.
 * 
 * @author Luciano
 */
public class Data {
    private int dia;
    private int mês;
    private int ano;
    
    public Data(){
        
    }
    public Data(int dia, int mês){
        this.dia = dia;
        this.mês = mês;
        this.ano = 2000;
    }
    public Data(int dia, int mês, int ano){
        this.dia = dia;
        this.mês = mês;
        this.ano = ano;
    }
    
    public String getData(){
        String out = "";
        if(dia<10) out+="0";
        out+=dia+"/";
        if(mês<10) out+="0";
        out+=mês+"/";
        out+=ano;
        return out;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMês() {
        return mês;
    }

    public void setMês(int mês) {
        this.mês = mês;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
}