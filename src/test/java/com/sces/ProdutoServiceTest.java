/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoServiceTest {

    private ProdutoRepository repo;
    private ProdutoService service;

    @BeforeEach
    public void setUp() {
        repo = new ProdutoRepository();
        service = new ProdutoService(repo);
    }

    @Test
    public void testServiceDeveCadastrarProdutoValido() {
        boolean resultado = service.cadastrarProduto("Arroz", "Pacote de 5kg", 10);

        assertTrue(resultado);
        assertEquals(1, repo.listarProdutos().size());
    }

    @Test
    public void testServiceNaoDeveCadastrarProdutoComNomeDuplicado() {
        repo.adicionarProduto("Feijão", "Tipo carioca", 5);

        boolean resultado = service.cadastrarProduto("Feijão", "Outro tipo", 3);

        assertFalse(resultado);
        assertEquals(1, repo.listarProdutos().size());
    }

    @Test
    public void testServiceNaoDeveCadastrarProdutoComQuantidadeNegativa() {
        boolean resultado = service.cadastrarProduto("Macarrão", "Espaguete", -2);

        assertFalse(resultado);
        assertTrue(repo.listarProdutos().isEmpty());
    }
}