/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fatecpg.poo;

/**
 *
 * @author Luciano
 */
public class Pessoa {
    private String nome;
    private Pessoa pai;
    private Pessoa mãe;
    private Data nascimento;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public Pessoa(String nome, Data nascimento) {
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public Pessoa(String nome, Pessoa pai, Pessoa mãe, Data nascimento) {
        this.nome = nome;
        this.pai = pai;
        this.mãe = mãe;
        this.nascimento = nascimento;
    }
    
    public int getIdade(int anoAtual){
        return anoAtual - getNascimento().getAno();
    }
    
    public Pessoa getAvôPaterno(){
        return getPai().getPai();
    }
    
    public Pessoa getAvôMaterno(){
        return getMãe().getPai();
    }

    public Data getNascimento() {
        return nascimento;
    }

    public void setNascimento(Data nascimento) {
        this.nascimento = nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pessoa getPai() {
        return pai;
    }

    public void setPai(Pessoa pai) {
        this.pai = pai;
    }

    public Pessoa getMãe() {
        return mãe;
    }

    public void setMãe(Pessoa mãe) {
        this.mãe = mãe;
    }
    
}