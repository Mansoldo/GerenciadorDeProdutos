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
    
    public Categoria(int id){
        this.id = id;
    }
    
    public Categoria (String nome, int id){
        this.nome = nome;
        this.id = id;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
}
