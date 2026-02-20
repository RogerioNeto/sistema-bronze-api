package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.entity.Comanda;
import java.util.List;
import java.util.Optional;

public interface ComandaService {
    Comanda salvarComanda(Comanda comanda);
    List<Comanda> listarTodas();
    Optional<Comanda> buscarPorId(Long id);
    Optional<Comanda> atualizarComanda(Long id, Comanda comanda);
    boolean excluirComanda(Long id);
}
