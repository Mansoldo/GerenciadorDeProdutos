
package Model;

import java.util.Date;

/**
 *
 * @author Mansoldo
 */
public class Produto {
    
    private int idProduto;
    private String nome;
    private String descricao;
    private double valorCompra;
    private double valorVenda;
    private int quantidade;
    private int status;
    private Date dataCadastro;
    private int idEditar;
    
    public Produto(){        
    }

    public Produto(String nome, String descricao, double valorCompra, double valorVenda, int quantidade, int status, Date dataCadastro) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.quantidade = quantidade;
        this.status = status;
        this.dataCadastro = dataCadastro;
    }
    
    public Produto(int id, String nome, String descricao, double valorCompra, double valorVenda, int quantidade, int status) {
        this.idProduto = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.quantidade = quantidade;
        this.status = status;        
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }           

    /**
     * @return the idEditar
     */
    public int getIdEditar() {
        return idEditar;
    }

    /**
     * @param idEditar the idEditar to set
     */
    public void setIdEditar(int idEditar) {
        this.idEditar = idEditar;
    }
}
