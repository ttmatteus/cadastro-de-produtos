package facade;

import model.Produto;
import model.dao.ProdutoDAO;
import java.util.List;
import view.ProdutoView;

public class ProdutoFacade {
    private final ProdutoDAO dao;
    private final ProdutoView view;

    public ProdutoFacade() {
        this.dao = new ProdutoDAO();
        this.view = new ProdutoView();
    }

    public Produto buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public void cadastrarProduto() {
        Produto p = new Produto();
        String nome = view.obterNomeProduto();
        p.setNome(nome);

        double preco = view.obterPrecoProduto();
        p.setPreco(preco);

        int quantidade = view.obterQuantidadeProduto();
        p.setQuantidade(quantidade);

        if (dao.verificarProdutoExistente(p)) {
            view.exibirMensagem(" Produto com nome '" + p.getNome() + "' já existe. Cadastro não realizado.");
            return;
        }

        dao.salvar(p);
        view.exibirMensagem("Produto cadastrado com sucesso!");
    }

    public void listarProdutos() {
        List<Produto> produtos = dao.listarTodos();
        view.exibirListaProdutos(produtos);
    }

    public void editarProduto() {
        int id = view.obterIdProduto();
        Produto existente = buscarPorId(id);
        if (existente == null) {
            view.exibirMensagem("Produto não encontrado.");
            return;
        }

        String nome = view.obterNomeProdutoExistente(existente);
        double preco = view.validarPreco(existente.getPreco());
        int quantidade = view.validarQuantidade(existente.getQuantidade());

        Produto atualizado = new Produto(id, nome, preco, quantidade);
        dao.atualizar(atualizado);
        view.exibirMensagem("Produto atualizado com sucesso.");
    }

    public void deletarProduto() {
        int id = view.obterIdProduto();
        Produto produto = buscarPorId(id);
        if (produto == null) {
            view.exibirMensagem("Produto não encontrado.");
            return;
        }

        dao.deletar(id);
        view.exibirMensagem("Produto deletado com sucesso.");
    }

    public void buscarPorId() {
        int id = view.obterIdProduto();
        Produto produto = buscarPorId(id);

        if (produto == null) {
            view.exibirMensagem("Produto não encontrado.");
        } else {
            view.exibirDetalhesProduto(produto);
        }
    }

    public void exportarProdutosParaCSV() {
        dao.exportarProdutosParaCSV();
    }
}