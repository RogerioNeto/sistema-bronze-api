package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.entity.Conta;
import java.util.List;
import java.util.Optional;

public interface ContaService {
    Conta salvarConta(Conta conta);
    Conta atualizarConta(Long id, Conta conta);
    List<Conta> listarContas();
    Optional<Conta> buscarContaPorId(Long id);
    void deletarConta(Long id);
}
