package com.sces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoServiceTest {

    private ProdutoRepository repo;
    private ProdutoService service;

    @BeforeEach
    void setUp() {
        repo = new ProdutoRepository();
        service = new ProdutoService(repo);
    }

    @Test
    void adicionarEstoque_sucesso() {
        // Arrange
        boolean cadastrado = service.cadastrarProduto("Feijão", "Tipo carioca", 3);
        assertTrue(cadastrado);

        Produto p = service.listarTodosOsProdutos().get(0);
        int id = p.getId();
        int qtdAntiga = p.getQuantidade();

        // Act
        boolean ok = service.adicionarUnidadesAoEstoque(id, 7);

        // Assert
        assertTrue(ok, "Esperado true para adicionar estoque com ID válido");
        Produto depois = service.listarTodosOsProdutos().get(0);
        assertEquals(qtdAntiga + 7, depois.getQuantidade());
    }

    @Test
    void adicionarEstoque_falha_idInexistente() {
        // Arrange: nenhum produto cadastrado
        assertTrue(service.listarTodosOsProdutos().isEmpty());

        // Act
        boolean ok = service.adicionarUnidadesAoEstoque(1234, 5);

        // Assert
        assertFalse(ok, "Esperado false quando o ID não existe");
        assertTrue(service.listarTodosOsProdutos().isEmpty(), "Não deve criar/alterar nada ao falhar");
    }
}
