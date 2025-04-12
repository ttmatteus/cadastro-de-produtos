package model.dao;

import model.Produto;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements ProdutoDAOInterface {

    public ProdutoDAO() {
        criarTabelaSeNaoExistir();
    }

    public boolean verificarProdutoExistente(Produto produto) {
        String sql = "SELECT COUNT(*) FROM produto WHERE LOWER(nome) = LOWER(?)";
        try (Connection conn = ConexaoSingleton.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar produto existente: " + e.getMessage());
        }
        return false;
    }

    public void exportarProdutosParaCSV() {
        String sql = "SELECT * FROM produto";
        try (Connection conn = ConexaoSingleton.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            String caminhoArquivo = "produtos.csv";
            FileWriter fileWriter = new FileWriter(caminhoArquivo);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("ID,Nome,Preço,Quantidade");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");

                String precoFormatado = String.format("%.2f", preco).replace(",", ".");

                printWriter.printf("%d,%s,%s,%d%n", id, nome, precoFormatado, quantidade);
            }

            printWriter.close();
            System.out.println("Produtos exportados para 'produtos.csv' com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo CSV: " + e.getMessage());
        }
    }


    @Override
    public void salvar(Produto produto) {
        if (verificarProdutoExistente(produto)) {
            System.out.println("Produto com nome '" + produto.getNome() + "' já existe. Cadastro não realizado.");
            return;
        }
        String sql = "INSERT INTO produto (nome, preco, quantidade) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoSingleton.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.executeUpdate();
            System.out.println("Produto salvo com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar produto: " + e.getMessage());
        }
    }


    public void atualizar(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, preco = ?, quantidade = ? WHERE id = ?";
        try (Connection conn = ConexaoSingleton.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getId());

            stmt.executeUpdate();

            System.out.println("Produto atualizado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }


    public void deletar(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        try (Connection conn = ConexaoSingleton.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Produto deletado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar produto: " + e.getMessage());
        }
    }


    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id = ?";
        try (Connection conn = ConexaoSingleton.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade")
                );
            } else {
                System.out.println("Produto com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try (Connection conn = ConexaoSingleton.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setQuantidade(rs.getInt("quantidade"));
                produtos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
        return produtos;
    }

    public void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS produto (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "preco REAL, " +
                "quantidade INTEGER);";
        try (Connection conn = ConexaoSingleton.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
            System.out.println("Tabela 'produto' verificada/criada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }
}
