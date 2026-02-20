package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.entity.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoService {
    Produto salvarProduto(Produto produto);
    Produto atualizarProduto(Long id, Produto produto);
    List<Produto> listarProdutos();
    Optional<Produto> buscarProdutoPorId(Long id);
    void deletarProduto(Long id);
}
