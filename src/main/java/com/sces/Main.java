package com.sces;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProdutoRepository repo = new ProdutoRepository();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Cadastro de Produtos ===");
        while (true) {
            System.out.print("\nDigite o nome do produto (ou 'fim' para encerrar): ");
            String nome = scanner.nextLine();
            if (nome.equalsIgnoreCase("fim")) break;

            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();

            System.out.print("Quantidade: ");
            int quantidade;
            try {
                quantidade = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Quantidade inválida. Tente novamente.");
                continue;
            }

            boolean sucesso = repo.adicionarProduto(nome, descricao, quantidade);
            if (sucesso) {
                System.out.println("Produto adicionado com sucesso!");
            } else {
                System.out.println("Erro ao adicionar produto (nome duplicado ou quantidade inválida).");
            }
        }

        System.out.println("\n=== Lista de Produtos Cadastrados ===");
        for (Produto p : repo.listarProdutos()) {
            System.out.println(p);
        }

        scanner.close();
    }
}
