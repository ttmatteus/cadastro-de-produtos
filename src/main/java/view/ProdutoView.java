package view;

import model.Produto;
import model.validator.ProdutoValidador;
import java.util.List;
import java.util.Scanner;

public class ProdutoView {
    private final Scanner sc;

    public ProdutoView() {
        this.sc = new Scanner(System.in);
    }

    public String obterNomeProduto() {
        String nome;
        while (true) {
            System.out.print("Nome do Produto: ");
            nome = sc.nextLine().trim();
            if (ProdutoValidador.validarNome(nome)) {
                break;
            }
        }
        return nome;
    }

    public double obterPrecoProduto() {
        while (true) {
            System.out.print("Preço do Produto: ");
            String precoStr = sc.nextLine().trim();
            if (ProdutoValidador.validarPreco(precoStr)) {
                return Double.parseDouble(precoStr);
            }
        }
    }

    public int obterQuantidadeProduto() {
        while (true) {
            System.out.print("Quantidade em Estoque: ");
            String qtdStr = sc.nextLine().trim();
            if (ProdutoValidador.validarQuantidade(qtdStr)) {
                return Integer.parseInt(qtdStr);
            }
        }
    }

    public int obterIdProduto() {
        System.out.print("Digite o ID do produto: ");
        int id = sc.nextInt();
        sc.nextLine();
        return id;
    }

    public String obterNomeProdutoExistente(Produto existente) {
        String nome;
        while (true) {
            System.out.print("Novo nome (" + existente.getNome() + "): ");
            nome = sc.nextLine().trim();
            if (nome.isBlank()) {
                return existente.getNome();
            }
            if (ProdutoValidador.validarNome(nome)) {
                return nome;
            }
        }
    }


    public double validarPreco(double precoAtual) {
        while (true) {
            System.out.print("Preço (" + precoAtual + "): ");
            String precoStr = sc.nextLine();
            if (precoStr.isBlank()) {
                return precoAtual;
            }
            if (ProdutoValidador.validarPreco(precoStr)) {
                return Double.parseDouble(precoStr);
            }
        }
    }

    public int validarQuantidade(int quantidadeAtual) {
        while (true) {
            System.out.print("Quantidade (" + quantidadeAtual + "): ");
            String qtdStr = sc.nextLine();
            if (qtdStr.isBlank()) {
                return quantidadeAtual;
            }
            if (ProdutoValidador.validarQuantidade(qtdStr)) {
                return Integer.parseInt(qtdStr);
            }
        }
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void exibirListaProdutos(List<Produto> produtos) {
        System.out.println("\n--- Lista de Produtos ---");
        if (produtos.isEmpty()) {
            System.out.println("Não há produtos cadastrados.");
        } else {
            System.out.printf("%-5s %-25s %-10s %-12s\n", "ID", "Nome", "Preço", "Quantidade");
            System.out.println("--------------------------------------------------------------");
            for (Produto produto : produtos) {
                System.out.printf("%-5d %-25s R$ %-9.2f %-12d\n", produto.getId(), produto.getNome(), produto.getPreco(), produto.getQuantidade());
            }
        }
    }

    public void exibirDetalhesProduto(Produto produto) {
        System.out.println("\n--- Detalhes do Produto ---");
        System.out.printf("%-5s %-25s %-10s %-12s\n", "ID", "Nome", "Preço", "Quantidade");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-5d %-25s R$ %-9.2f %-12d\n", produto.getId(), produto.getNome(), produto.getPreco(), produto.getQuantidade());
    }
}
