package model.dao;

import model.Produto;
import java.util.List;

public interface ProdutoDAOInterface {
    void salvar(Produto produto);
    List<Produto> listarTodos();
}
