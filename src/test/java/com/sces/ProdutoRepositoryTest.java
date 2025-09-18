package com.sces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoRepositoryTest {

    private ProdutoRepository repo;

    @BeforeEach
    public void setUp() {
        repo = new ProdutoRepository();
    }

    @Test
    public void testAdicionarProdutoValido() {
        boolean resultado = repo.adicionarProduto("Arroz", "Pacote de 5kg", 10);
        assertTrue(resultado);
        assertEquals(1, repo.listarProdutos().size());
        Produto produto = repo.listarProdutos().get(0);
        assertEquals(1, produto.getId());
        assertEquals("Arroz", produto.getNome());
        assertEquals("Pacote de 5kg", produto.getDescricao());
        assertEquals(10, produto.getQuantidade());
    }

    @Test
    public void testNomeDuplicado() {
        repo.adicionarProduto("Feijão", "Tipo carioca", 5);
        boolean resultado = repo.adicionarProduto("Feijão", "Outro tipo", 3);
        assertFalse(resultado);
        assertEquals(1, repo.listarProdutos().size());
    }

    @Test
    public void testQuantidadeNegativa() {
        boolean resultado = repo.adicionarProduto("Macarrão", "Espaguete", -2);
        assertFalse(resultado);
        assertEquals(0, repo.listarProdutos().size());
    }

    @Test
    public void testIdSequencial() {
        repo.adicionarProduto("Arroz", "Pacote de 5kg", 10);
        repo.adicionarProduto("Feijão", "Tipo carioca", 5);
        Produto p1 = repo.listarProdutos().get(0);
        Produto p2 = repo.listarProdutos().get(1);
        assertEquals(1, p1.getId());
        assertEquals(2, p2.getId());
    }
}
