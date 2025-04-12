package controller;

import facade.ProdutoFacade;
import java.util.Scanner;

public class ProdutoController {
    private final ProdutoFacade produtoFacade;

    public ProdutoController() {
        this.produtoFacade = new ProdutoFacade();
    }

    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Editar Produto");
            System.out.println("4 - Deletar Produto");
            System.out.println("5 - Buscar Produto");
            System.out.println("6 - Exportar Produtos para CSV");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            String input = sc.nextLine();
            try {
                opcao = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número válido.");
                continue;
            }

            switch (opcao) {
                case 1 -> produtoFacade.cadastrarProduto();
                case 2 -> produtoFacade.listarProdutos();
                case 3 -> produtoFacade.editarProduto();
                case 4 -> produtoFacade.deletarProduto();
                case 5 -> produtoFacade.buscarPorId();
                case 6 -> produtoFacade.exportarProdutosParaCSV();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
        sc.close();
    }
}
