package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.entity.Compra;
import java.util.List;
import java.util.Optional;

public interface CompraService {
    Compra salvarCompra(Compra compra);
    Compra atualizarCompra(Long id, Compra compra);
    List<Compra> listarCompras();
    Optional<Compra> buscarCompraPorId(Long id);
    void deletarCompra(Long id);
}
