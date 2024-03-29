/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOProduto;
import Model.Produto;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mansoldo
 */
public class ControllerProduto {

    //Método que recebe os dados para salvar um produto
    public static boolean salvarProduto(String nome, String descricao, double valorCompra, double valorVenda,
            int quantidade, int status, Date dataCadastro) {
        Produto produto = new Produto(nome, descricao, valorCompra, valorVenda, quantidade, status, dataCadastro);
        return DAOProduto.salvarDAOProduto(produto);
    }

    //Método que retorna um Array de String dos produtos para popular tabela
    public static ArrayList<String[]> getProdutos() {

        ArrayList<Produto> produtos = DAOProduto.getProdutos();
        ArrayList<String[]> listaVazia = new ArrayList<>();

        for (int i = 0; i < produtos.size(); i++) {
            listaVazia.add(new String[]{
                String.valueOf(produtos.get(i).getIdProduto()),
                String.valueOf(produtos.get(i).getNome()),
                String.valueOf(produtos.get(i).getDescricao()),
                String.valueOf(produtos.get(i).getValorCompra()),
                String.valueOf(produtos.get(i).getValorVenda()),
                String.valueOf(produtos.get(i).getQuantidade()),
                String.valueOf(produtos.get(i).getStatus()),
                String.valueOf(produtos.get(i).getDataCadastro())
            }
            );
        }
        return listaVazia;
    }

    //Método que recebe a lista de Produtos
    public static ArrayList<Produto> getProdutoList() {
        ArrayList<Produto> produtos = DAOProduto.getProdutos();
        return produtos;
    }

    //Método que recebe os dados de produto para Editar
    public static boolean controllerEditarProduto(int ID, String nome, String descricao, double valorCompra, double valorVenda,
            int quantidade, int status) {
        
        Produto produto = new Produto(ID ,nome, descricao, valorCompra, valorVenda, quantidade, status);
         return DAOProduto.daoEditarProduto(produto);
    //Informa o ID a ser excluído pelo comando SQL na DAOProduto
    }
    public static boolean ExcluirProduto(int ID) {
        return DAOProduto.excluirDAOProduto(ID);
    }

}
