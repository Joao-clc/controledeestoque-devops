package com.sces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoRepositoryTest {

    private ProdutoRepository repo;

    @BeforeEach
    void setUp() {
        repo = new ProdutoRepository();
    }

    @Test
    void adicionarUnidades_sucesso() {
        // Arrange: cria um produto base
        boolean okCadastro = repo.adicionarProduto("Arroz", "Pacote 5kg", 10);
        assertTrue(okCadastro);

        Produto p = repo.listarProdutos().get(0);
        int id = p.getId();
        int qtdAntiga = p.getQuantidade();

        // Act: adiciona +5
        boolean ok = repo.adicionarUnidades(id, 5);

        // Assert
        assertTrue(ok, "Esperado true para ID válido e quantidade > 0");
        Produto apos = repo.buscarPorId(id);
        assertNotNull(apos);
        assertEquals(qtdAntiga + 5, apos.getQuantidade());
    }

    @Test
    void adicionarUnidades_falha_idInexistente() {
        // Arrange: repositório vazio (ou sem o ID 999)
        assertTrue(repo.listarProdutos().isEmpty());

        // Act
        boolean ok = repo.adicionarUnidades(999, 5);

        // Assert
        assertFalse(ok, "Esperado false quando o ID não existe");
        assertTrue(repo.listarProdutos().isEmpty(), "Não deve criar/alterar nada ao falhar");
    }
}

