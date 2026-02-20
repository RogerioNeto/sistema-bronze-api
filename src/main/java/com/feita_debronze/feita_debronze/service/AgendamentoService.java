package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.dto.AgendamentoRequestDTO;
import com.feita_debronze.feita_debronze.entity.Agendamento;
import java.util.List;
import java.util.Optional;

public interface AgendamentoService {
    Agendamento salvarAgendamento(AgendamentoRequestDTO agendamentoDTO);
    Agendamento atualizarAgendamento(Long id, AgendamentoRequestDTO agendamentoDTO);
    Agendamento aprovarAgendamento(Long id);
    List<Agendamento> listarAgendamentos();
    Optional<Agendamento> buscarAgendamentoPorId(Long id);
    void deletarAgendamento(Long id);
}
