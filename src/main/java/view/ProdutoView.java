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
        double preco;
        while (true) {
            System.out.print("Preço do Produto: ");
            String precoStr = sc.nextLine().trim();
            try {
                preco = Double.parseDouble(precoStr);
                if (ProdutoValidador.validarPreco(preco)) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Por favor, insira um valor numérico válido para o preço.");
            }
        }
        return preco;
    }

    public int obterQuantidadeProduto() {
        int quantidade;
        while (true) {
            System.out.print("Quantidade em Estoque: ");
            String qtdStr = sc.nextLine().trim();
            try {
                quantidade = Integer.parseInt(qtdStr);
                if (ProdutoValidador.validarQuantidade(quantidade)) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Por favor, insira um valor numérico válido para a quantidade.");
            }
        }
        return quantidade;
    }

    public int obterIdProduto() {
        System.out.print("Digite o ID do produto: ");
        return sc.nextInt();
    }

    public String obterNomeProdutoExistente(Produto existente) {
        System.out.print("Novo nome (" + existente.getNome() + "): ");
        String nome = sc.nextLine();
        return nome.isBlank() ? existente.getNome() : nome;
    }

    public double validarPreco(double precoAtual) {
        double preco = -1;
        while (preco <= 0) {
            try {
                System.out.print("Preço (" + precoAtual + "): ");
                String precoStr = sc.nextLine();
                preco = precoStr.isBlank() ? precoAtual : Double.parseDouble(precoStr);

                if (ProdutoValidador.validarPreco(preco)) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Por favor, insira um valor numérico válido para o preço.");
            }
        }
        return preco;
    }

    public int validarQuantidade(int quantidadeAtual) {
        int quantidade = -1;
        while (quantidade < 0) {
            try {
                System.out.print("Quantidade (" + quantidadeAtual + "): ");
                String qtdStr = sc.nextLine();
                quantidade = qtdStr.isBlank() ? quantidadeAtual : Integer.parseInt(qtdStr);

                if (ProdutoValidador.validarQuantidade(quantidade)) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Por favor, insira um valor numérico válido para a quantidade.");
            }
        }
        return quantidade;
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
