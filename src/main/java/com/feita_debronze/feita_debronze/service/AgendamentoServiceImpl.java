package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.dto.AgendamentoRequestDTO;
import com.feita_debronze.feita_debronze.entity.Agendamento;
import com.feita_debronze.feita_debronze.entity.Unidade;
import com.feita_debronze.feita_debronze.repository.AgendamentoRepository;
import com.feita_debronze.feita_debronze.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final UnidadeRepository unidadeRepository; // Injetado

    public AgendamentoServiceImpl(AgendamentoRepository agendamentoRepository, UnidadeRepository unidadeRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.unidadeRepository = unidadeRepository;
    }

    @Override
    public Agendamento salvarAgendamento(AgendamentoRequestDTO agendamentoDTO) {
        Unidade unidade = unidadeRepository.findByNome(agendamentoDTO.getUnidade())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada: " + agendamentoDTO.getUnidade()));

        Agendamento agendamento = new Agendamento();
        agendamento.setNomeCliente(agendamentoDTO.getNomeCliente());
        agendamento.setTelefone(agendamentoDTO.getTelefone());
        agendamento.setDataHora(agendamentoDTO.getDataHora());
        agendamento.setProcedimento(agendamentoDTO.getProcedimento());
        agendamento.setUnidade(unidade); // Salva o objeto Unidade

        if (agendamentoDTO.getStatus() == null || agendamentoDTO.getStatus().isEmpty()) {
            agendamento.setStatus("PENDENTE");
        } else {
            agendamento.setStatus(agendamentoDTO.getStatus());
        }

        return agendamentoRepository.save(agendamento);
    }

    @Override
    public Agendamento atualizarAgendamento(Long id, AgendamentoRequestDTO agendamentoDTO) {
        Unidade unidade = unidadeRepository.findByNome(agendamentoDTO.getUnidade())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada: " + agendamentoDTO.getUnidade()));

        return agendamentoRepository.findById(id)
                .map(agendamento -> {
                    agendamento.setNomeCliente(agendamentoDTO.getNomeCliente());
                    agendamento.setTelefone(agendamentoDTO.getTelefone());
                    agendamento.setDataHora(agendamentoDTO.getDataHora());
                    agendamento.setProcedimento(agendamentoDTO.getProcedimento());
                    agendamento.setUnidade(unidade); // Atualiza o objeto Unidade

                    if (agendamentoDTO.getStatus() != null) {
                        agendamento.setStatus(agendamentoDTO.getStatus());
                    }
                    return agendamentoRepository.save(agendamento);
                })
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado com id " + id));
    }

    @Override
    public Agendamento aprovarAgendamento(Long id) {
        return agendamentoRepository.findById(id)
                .map(agendamento -> {
                    agendamento.setStatus("APROVADO");
                    return agendamentoRepository.save(agendamento);
                })
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado com id " + id));
    }

    @Override
    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.findAll();
    }

    @Override
    public Optional<Agendamento> buscarAgendamentoPorId(Long id) {
        return agendamentoRepository.findById(id);
    }

    @Override
    public void deletarAgendamento(Long id) {
        agendamentoRepository.deleteById(id);
    }
}
