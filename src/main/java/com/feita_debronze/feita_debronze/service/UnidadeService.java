package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.entity.Unidade;
import java.util.List;
import java.util.Optional;

public interface UnidadeService {
    Unidade salvarUnidade(Unidade unidade);
    Unidade atualizarUnidade(Long id, Unidade unidade);
    List<Unidade> listarUnidades();
    Optional<Unidade> buscarUnidadePorId(Long id);
    void deletarUnidade(Long id);
}
