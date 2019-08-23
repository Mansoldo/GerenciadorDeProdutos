/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Produto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mansoldo
 */
public class DAOProduto {

    //Método para obter conexão
    private static Connection obterConexao() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/produtobd?useTimezone=true&serverTimezone=UTC", "root", "adminadmin");
        return conexao;
    }

    //Método para salvar produto
    public static boolean salvarDAOProduto(Produto produto) {

        boolean retorno = false;

        try (Connection conexao = obterConexao()) {

            PreparedStatement comandoSQL = conexao.prepareStatement("INSERT INTO PRODUTOBD.PRODUTO"
                    + " (NOME, DESCRICAO, PRECO_COMPRA, PRECO_VENDA, QUANTIDADE, DISPONIVEL, DT_CADASTRO)"
                    + " VALUES (?,?,?,?,?,?,?)");

            comandoSQL.setString(1, produto.getNome());
            comandoSQL.setString(2, produto.getDescricao());
            comandoSQL.setDouble(3, produto.getValorCompra());
            comandoSQL.setDouble(4, produto.getValorVenda());
            comandoSQL.setInt(5, produto.getQuantidade());
            comandoSQL.setInt(6, produto.getStatus());
            comandoSQL.setDate(7, (Date) produto.getDataCadastro());

            int linhaAfetada = comandoSQL.executeUpdate();

            retorno = linhaAfetada > 0;

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            retorno = false;
        }
        return retorno;
    }

    //Método para pegar produtos
    public static ArrayList<Produto> getProdutos() {

        ArrayList<Produto> lista = new ArrayList<>();
        try (Connection conexao = obterConexao()) {

            PreparedStatement comandoSQL = conexao.prepareStatement("SELECT ID, NOME, DESCRICAO, PRECO_COMPRA, "
                    + "PRECO_VENDA, QUANTIDADE, DISPONIVEL, DT_CADASTRO "
                    + "FROM PRODUTO");

            ResultSet rs = comandoSQL.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Produto produto = new Produto();
                    produto.setIdProduto(rs.getInt("ID"));
                    produto.setNome(rs.getString("NOME"));
                    produto.setDescricao(rs.getString("DESCRICAO"));
                    produto.setValorCompra(rs.getDouble("PRECO_COMPRA"));
                    produto.setValorVenda(rs.getDouble("PRECO_VENDA"));
                    produto.setQuantidade(rs.getInt("QUANTIDADE"));
                    produto.setStatus(rs.getInt("DISPONIVEL"));
                    produto.setDataCadastro(rs.getDate("DT_CADASTRO"));
                    lista.add(produto);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            lista = null;
            ex.printStackTrace();
        }
        return lista;
    }

    //Método para editar produtos
    public static boolean daoEditarProduto(Produto p) {
        boolean retorno = false;

        try (Connection conexao = obterConexao()) {

            PreparedStatement comandoSQL = conexao.prepareStatement("UPDATE PRODUTOBD.PRODUTO\n"
                    + "SET  NOME = ?, DESCRICAO= ?, PRECO_COMPRA= ?, PRECO_VENDA= ?, QUANTIDADE= ?, DISPONIVEL= ?\n"
                    + "WHERE ID = ?");

            comandoSQL.setString(1, p.getNome());
            comandoSQL.setString(2, p.getDescricao());
            comandoSQL.setDouble(3, p.getValorCompra());
            comandoSQL.setDouble(4, p.getValorVenda());
            comandoSQL.setInt(5, p.getQuantidade());
            comandoSQL.setInt(6, p.getStatus());
            comandoSQL.setInt(7, p.getIdProduto());

            int linhaAfetada = comandoSQL.executeUpdate();

            retorno = linhaAfetada > 0;

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            retorno = false;
        }
        return retorno;
    }
    // Metódo que recebe como parametro o ID do produto e realiza a exclusão do bando de Dados
    public static boolean excluirDAOProduto(int ID) {

        boolean retorno = false;

        try (Connection conexao = obterConexao()) {

            PreparedStatement comandoSQL = conexao.prepareStatement("DELETE FROM PRODUTO WHERE ID = ?");

            comandoSQL.setInt(1, ID);

            int linha = comandoSQL.executeUpdate();

            retorno = linha > 0;

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return retorno;

    }

}
