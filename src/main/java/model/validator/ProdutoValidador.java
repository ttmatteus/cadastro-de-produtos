package model.validator;

public class ProdutoValidador {

    public static boolean validarNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            System.out.println("Nome do produto é obrigatório.");
            return false;
        }
        if (nome.length() < 3) {
            System.out.println("O nome do produto deve ter pelo menos 3 caracteres.");
            return false;
        }
        return true;
    }

    public static boolean validarPreco(String precoStr) {
        try {
            double preco = Double.parseDouble(precoStr);
            if (preco <= 0) {
                System.out.println("O preço deve ser maior que zero.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Por favor, insira um valor numérico válido para o preço.");
            return false;
        }
    }

    public static boolean validarQuantidade(String qtdStr) {
        try {
            int quantidade = Integer.parseInt(qtdStr);
            if (quantidade < 0) {
                System.out.println("A quantidade não pode ser negativa.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Por favor, insira um valor numérico válido para a quantidade.");
            return false;
        }
    }
}
