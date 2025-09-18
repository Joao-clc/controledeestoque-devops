package com.sces;

public class Main {
    public static void main(String[] args) {
        ProdutoRepository repo = new ProdutoRepository();

        // Teste: adicionar produtos
        boolean sucesso1 = repo.adicionarProduto("Arroz", "Pacote de 5kg", 10);
        boolean sucesso2 = repo.adicionarProduto("Feijão", "Tipo carioca", 5);
        boolean duplicado = repo.adicionarProduto("Arroz", "Outro pacote", 3); // Deve falhar

        // Teste: listar produtos
        System.out.println("Lista de produtos:");
        for (Produto p : repo.listarProdutos()) {
            System.out.println(p);
        }

        // Teste: adicionar unidades
        boolean adicionou = repo.adicionarUnidades(1, 5); // Adiciona 5 ao produto com ID 1
        boolean falhou = repo.adicionarUnidades(99, 10); // ID inexistente
        

        System.out.println("\nApós adicionar unidades:");
        for (Produto p : repo.listarProdutos()) {
            System.out.println(p);
        }
        
     // Teste: tentativa de adicionar produto com quantidade negativa
        boolean negativo = repo.adicionarProduto("Óleo", "Garrafa de 900ml", -5);
        System.out.println("\nTentativa de adicionar produto com quantidade negativa:");
        System.out.println("Produto rejeitado: " + !negativo);


        // Resultados dos testes
        System.out.println("\nResultados:");
        System.out.println("Produto 1 adicionado: " + sucesso1);
        System.out.println("Produto 2 adicionado: " + sucesso2);
        System.out.println("Produto duplicado rejeitado: " + !duplicado);
        System.out.println("Unidades adicionadas ao produto 1: " + adicionou);
        System.out.println("Falha ao adicionar ao produto inexistente: " + !falhou);
    }
}
