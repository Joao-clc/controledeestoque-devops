package com.sces;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ProdutoRepository repo = new ProdutoRepository();
        ProdutoService service = new ProdutoService(repo);

        try (Scanner sc = new Scanner(System.in)) {
            boolean executando = true;
            while (executando) {
                imprimirMenu();
                int opcao = lerInteiro(sc, "Escolha uma opção: ");

                switch (opcao) {
                    case 1 -> cadastrarProduto(sc, service);
                    case 2 -> listarProdutos(service);
                    case 3 -> adicionarEstoque(sc, service);
                    case 4 -> {
                        System.out.println("Encerrando... Até logo!");
                        executando = false;
                    }
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }

                if (executando) {
                    System.out.println();
                    aguardarEnter(sc);
                }
            }
        }
    }

    private static void cadastrarProduto(Scanner sc, ProdutoService service) {
        System.out.println("\n== Cadastrar Produto ==");
        String nome = lerNaoVazio(sc, "Nome: ");
        String descricao = lerNaoVazio(sc, "Descrição: ");
        int quantidadeInicial = lerInteiro(sc, "Quantidade inicial (>= 0): ");

        try {
            boolean cadastrado = service.cadastrarProduto(nome, descricao, quantidadeInicial);
            if (cadastrado) {
                System.out.println("Produto cadastrado com sucesso!");
            } else {
                System.out.println("Cadastro recusado: verifique regras (nome duplicado? quantidade negativa?).");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao cadastrar: " + e.getMessage());
        }
    }

    private static void listarProdutos(ProdutoService service) {
        System.out.println("\n== Listar Produtos ==");
        try {
            List<Produto> produtos = service.listarTodosOsProdutos();
            if (produtos == null || produtos.isEmpty()) {
                System.out.println("Nenhum produto cadastrado");
                return;
            }

            System.out.printf("%-5s %-25s %-45s %10s%n", "ID", "Nome", "Descrição", "Qtd.");
            System.out.println("-------------------------------------------------------------------------------------------");
            for (Produto p : produtos) {
                System.out.printf("%-5d %-25s %-45s %10d%n",
                        p.getId(),
                        safe(p.getNome(), 25),
                        safe(p.getDescricao(), 45),
                        p.getQuantidade());
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
    }

    private static void adicionarEstoque(Scanner sc, ProdutoService service) {
        System.out.println("\n== Adicionar Estoque ==");
        int id = lerInteiro(sc, "ID do produto: ");
        int qtd = lerInteiro(sc, "Quantidade a adicionar (> 0): ");

        try {
            boolean ok = service.adicionarUnidadesAoEstoque(id, qtd);
            if (ok) {
                System.out.println("Estoque atualizado com sucesso!");
            } else {
                System.out.println("Não foi possível atualizar estoque. Verifique o ID/quantidade.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Erro: produto não encontrado (ID: " + id + ").");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao adicionar estoque: " + e.getMessage());
        }
    }

    private static void imprimirMenu() {
        System.out.println("""
                --- GESTÃO DE PRODUTOS ---
                1. Cadastrar Produto
                2. Listar Produtos
                3. Adicionar Estoque
                4. Sair
                """);
    }

    private static int lerInteiro(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String entrada = sc.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número inteiro.");
            }
        }
    }

    private static String lerNaoVazio(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Campo obrigatório. Tente novamente.");
        }
    }

    private static void aguardarEnter(Scanner sc) {
        System.out.print("Pressione ENTER para continuar...");
        sc.nextLine();
    }

    private static String safe(String s, int max) {
        if (s == null) return "";
        if (s.length() <= max) return s;
        return s.substring(0, Math.max(0, max - 1)) + "…";
    }
}
// teste