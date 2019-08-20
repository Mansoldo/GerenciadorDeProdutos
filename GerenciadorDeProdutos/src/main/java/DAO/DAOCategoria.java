package DAO;

// @author diego.amalmeida
import Model.Categoria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOCategoria {

    private static Connection obterConexao() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3307/produtobd?useTimezone=true&serverTimezone=UTC", "root", "");
        return conexao;
    }

    public static int getIdProduto() {

        int id_produto = 0;

        try (Connection conexao = obterConexao()) {

            PreparedStatement comandoSQL = conexao.prepareStatement("SELECT MAX(ID) AS ID FROM PRODUTO");

            ResultSet rs = comandoSQL.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    id_produto = rs.getInt("ID");
                }

            }
        } catch (ClassNotFoundException | SQLException ex) {

            ex.printStackTrace();
        }
        return id_produto;
    }

    public static void associarCategoria(Categoria id_categoria) {

        int id_produto = getIdProduto();

        try (Connection conexao = obterConexao()) {

            PreparedStatement comandoSQL = conexao.prepareStatement("INSERT INTO PRODUTOBD.PRODUTO_CATEGORIA (ID_PRODUTO, ID_CATEGORIA) VALUES (?,?)");

            comandoSQL.setInt(1, id_produto);
            comandoSQL.setInt(2, id_categoria.getId());

            comandoSQL.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

    }
}
