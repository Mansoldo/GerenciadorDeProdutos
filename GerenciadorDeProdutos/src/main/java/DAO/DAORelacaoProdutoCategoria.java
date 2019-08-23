/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.RelacaoProdutoCategoria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel.rvital
 */
public class DAORelacaoProdutoCategoria {

    //Método para obter Conexão
    private static Connection obterConexao() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/produtobd?useTimezone=true&serverTimezone=UTC", "root", "");
        return conexao;
    }

    //Método para obter a relação de produtos com categorias
    public static ArrayList<RelacaoProdutoCategoria> getRelacaoProdutoCategoria() {
        ArrayList<RelacaoProdutoCategoria> lista = new ArrayList<>();
        try (Connection conexao = obterConexao()) {

            PreparedStatement comandoSQL = conexao.prepareStatement("SELECT ID_PRODUTO, ID_CATEGORIA "
                    + "FROM PRODUTO_CATEGORIA");

            ResultSet rs = comandoSQL.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    RelacaoProdutoCategoria relacao = new RelacaoProdutoCategoria();
                    relacao.setIdProduto(rs.getInt("ID_PRODUTO"));
                    relacao.setIdCategoria(rs.getInt("ID_CATEGORIA"));
                    lista.add(relacao);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            lista = null;
            ex.printStackTrace();
        }
        return lista;
    }

    //Método que efetua a exclusão da relação
    public static boolean excluirRelacao(int ID) {

        boolean retorno = false;

        try (Connection conexao = obterConexao()) {

            PreparedStatement comandoSQL = conexao.prepareStatement("DELETE FROM PRODUTO_CATEGORIA WHERE ID_PRODUTO = ?");

            comandoSQL.setInt(1, ID);

            int linha = comandoSQL.executeUpdate();

            retorno = linha > 0;

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }
}
