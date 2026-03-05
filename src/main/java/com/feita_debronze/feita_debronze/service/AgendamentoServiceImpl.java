package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.dto.AgendamentoRequestDTO;
import com.feita_debronze.feita_debronze.entity.Agendamento;
import com.feita_debronze.feita_debronze.entity.Cliente;
import com.feita_debronze.feita_debronze.entity.Unidade;
import com.feita_debronze.feita_debronze.repository.AgendamentoRepository;
import com.feita_debronze.feita_debronze.repository.ClienteRepository;
import com.feita_debronze.feita_debronze.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final UnidadeRepository unidadeRepository;
    private final ClienteRepository clienteRepository;

    public AgendamentoServiceImpl(AgendamentoRepository agendamentoRepository, UnidadeRepository unidadeRepository, ClienteRepository clienteRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.unidadeRepository = unidadeRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Agendamento salvarAgendamento(AgendamentoRequestDTO agendamentoDTO) {
        // Lógica para criar ou encontrar o cliente
        Cliente cliente = criarOuBuscarCliente(agendamentoDTO);

        Unidade unidade = null;
        if (agendamentoDTO.getUnidadeId() != null) {
            unidade = unidadeRepository.findById(agendamentoDTO.getUnidadeId())
                    .orElseThrow(() -> new RuntimeException("Unidade não encontrada com id: " + agendamentoDTO.getUnidadeId()));
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setDataHora(agendamentoDTO.getDataHora());
        agendamento.setProcedimento(agendamentoDTO.getProcedimento());
        agendamento.setUnidade(unidade);

        if (agendamentoDTO.getStatus() == null || agendamentoDTO.getStatus().isEmpty()) {
            agendamento.setStatus("PENDENTE");
        } else {
            agendamento.setStatus(agendamentoDTO.getStatus());
        }

        return agendamentoRepository.save(agendamento);
    }

    private Cliente criarOuBuscarCliente(AgendamentoRequestDTO agendamentoDTO) {
        return clienteRepository.findByTelefone(agendamentoDTO.getTelefone())
                .orElseGet(() -> {
                    Cliente novoCliente = new Cliente();
                    novoCliente.setNome(agendamentoDTO.getNomeCliente());
                    novoCliente.setTelefone(agendamentoDTO.getTelefone());
                    return clienteRepository.save(novoCliente);
                });
    }

    @Override
    public Agendamento atualizarAgendamento(Long id, AgendamentoRequestDTO agendamentoDTO) {
        Unidade unidade = null;
        if (agendamentoDTO.getUnidadeId() != null) {
            unidade = unidadeRepository.findById(agendamentoDTO.getUnidadeId())
                    .orElseThrow(() -> new RuntimeException("Unidade não encontrada com id: " + agendamentoDTO.getUnidadeId()));
        }

        Unidade finalUnidade = unidade;
        return agendamentoRepository.findById(id)
                .map(agendamento -> {
                    // Atualiza o cliente se necessário (opcional, dependendo da regra de negócio)
                    // Aqui estou assumindo que o cliente pode ser atualizado se o telefone mudar
                    if (!agendamento.getCliente().getTelefone().equals(agendamentoDTO.getTelefone())) {
                        Cliente cliente = criarOuBuscarCliente(agendamentoDTO);
                        agendamento.setCliente(cliente);
                    } else {
                        // Atualiza apenas o nome se o telefone for o mesmo
                        agendamento.getCliente().setNome(agendamentoDTO.getNomeCliente());
                        clienteRepository.save(agendamento.getCliente());
                    }

                    agendamento.setDataHora(agendamentoDTO.getDataHora());
                    agendamento.setProcedimento(agendamentoDTO.getProcedimento());
                    if (finalUnidade != null) {
                        agendamento.setUnidade(finalUnidade);
                    }

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
