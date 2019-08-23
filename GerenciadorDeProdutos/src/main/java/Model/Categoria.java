/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author diego
 */
public class Categoria {
    
    private int id;
    private String nome;
    
    //Construtor da classe Categoria
    public Categoria(int id){
        this.id = id;
    }
    //Sobrecarga do construtor
    public Categoria (String nome, int id){
        this.nome = nome;
        this.id = id;
    }
    //seta o nome
    public void setNome(String nome){
        this.nome = nome;
    }
    //retorna o nome
    public String getNome(){
        return nome;
    }
    //seta o ID
    public void setId(int id){
        this.id = id;
    }
    //retorna o ID
    public int getId(){
        return id;
    }
    
}
