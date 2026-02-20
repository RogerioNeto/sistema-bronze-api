package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.entity.Procedimento;
import java.util.List;
import java.util.Optional;

public interface ProcedimentoService {
    Procedimento salvarProcedimento(Procedimento procedimento);
    Procedimento atualizarProcedimento(Long id, Procedimento procedimento);
    List<Procedimento> listarProcedimentos();
    Optional<Procedimento> buscarProcedimentoPorId(Long id);
    void deletarProcedimento(Long id);
}
