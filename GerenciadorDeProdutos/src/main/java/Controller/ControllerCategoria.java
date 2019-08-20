
package Controller;

// @author diego.amalmeida

import DAO.DAOCategoria;
import Model.Categoria;

 
public class ControllerCategoria {
    
    public static void associarCategoria(int categoria){
        
        Categoria categoria_id = new Categoria(categoria);
        
        DAOCategoria.associarCategoria(categoria_id);
    }
    
}
