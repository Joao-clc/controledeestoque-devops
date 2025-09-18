/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sces;

import java.util.List;

public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public boolean cadastrarProduto(String nome, String descricao, int quantidade) {
        return produtoRepository.adicionarProduto(nome, descricao, quantidade);
    }

    public List<Produto> listarTodosOsProdutos() {
        return produtoRepository.listarProdutos();
    }

    public boolean adicionarUnidadesAoEstoque(int id, int quantidade) {
        // Apenas repassa a chamada. A lógica está no repositório.
        return produtoRepository.adicionarUnidades(id, quantidade);
    }
}