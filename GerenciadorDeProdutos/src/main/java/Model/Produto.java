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

    //construtor padrão
    public Produto() {
    }

    //construtor para novo produto
    public Produto(String nome, String descricao, double valorCompra, double valorVenda, int quantidade, int status, Date dataCadastro) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.quantidade = quantidade;
        this.status = status;
        this.dataCadastro = dataCadastro;
    }

    //construtor para editar produto
    public Produto(int id, String nome, String descricao, double valorCompra, double valorVenda, int quantidade, int status) {
        this.idProduto = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.quantidade = quantidade;
        this.status = status;
    }
    /**
     * @return the idProduto
     */
    public int getIdProduto() {
        return idProduto;
    }

    //seta o produto
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    //seta o nome
    public void setNome(String nome) {
        this.nome = nome;
    }
    //Retorna descrição
    public String getDescricao() {
        return descricao;
    }

    //seta descrição
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    /**
     * @return the valorCompra
     */
    public double getValorCompra() {
        return valorCompra;
    }

    //seta o valor de compra
    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }
    /**
     * @return the status
     */
    public double getValorVenda() {
        return valorVenda;
    }

    //seta o valor de Venda
    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }
    /**
     * @return the status
     */
    public int getQuantidade() {
        return quantidade;
    }

    //Seta a quantidade
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    //seta o status
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the dataCadastro
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }

    //Seta a data de cadastro
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
