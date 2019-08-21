package Controller;

// @author diego.amalmeida
import DAO.DAOCategoria;
import Model.Categoria;
import java.util.ArrayList;

public class ControllerCategoria {

    public static void associarCategoria(ArrayList<Integer> categoria) {

        for (Integer lista : categoria) {
            Categoria categoria_id = new Categoria(lista);
            DAOCategoria.associarCategoria(categoria_id);
        }
    }
}
