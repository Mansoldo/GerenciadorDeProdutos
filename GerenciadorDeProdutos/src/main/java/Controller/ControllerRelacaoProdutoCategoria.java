/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAORelacaoProdutoCategoria;
import Model.RelacaoProdutoCategoria;
import java.util.ArrayList;

/**
 *
 * @author gabriel.rvital
 */
public class ControllerRelacaoProdutoCategoria {

    //Método para obter a relação de Categoria com Produto
    public static ArrayList<RelacaoProdutoCategoria> getRelacao() {
        return DAORelacaoProdutoCategoria.getRelacaoProdutoCategoria();
    }

    //Método que efetuar a exclusão da relação no banco
    public static boolean excluirRelacao(int ID) {
        return DAORelacaoProdutoCategoria.excluirRelacao(ID);
    }

}
