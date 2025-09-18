package com.sces;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private List<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    public boolean adicionarProduto(String nome, String descricao, int quantidade) {
        if (quantidade < 0 || nomeDuplicado(nome)) {
            return false;
        }

        Produto novoProduto = new Produto(proximoId++, nome, descricao, quantidade);
        produtos.add(novoProduto);
        return true;
    }

    private boolean nomeDuplicado(String nome) {
        return produtos.stream().anyMatch(p -> p.getNome().equalsIgnoreCase(nome));
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto buscarPorId(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public boolean adicionarUnidades(int id, int quantidade) {
        if (quantidade <= 0) return false;

        Produto produto = buscarPorId(id);
        if (produto == null) return false;

        produto.setQuantidade(produto.getQuantidade() + quantidade);
        return true;
    }
}
