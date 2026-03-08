package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.entity.Agendamento;
import com.feita_debronze.feita_debronze.entity.Comanda;
import com.feita_debronze.feita_debronze.entity.ItemComanda;
import com.feita_debronze.feita_debronze.repository.AgendamentoRepository;
import com.feita_debronze.feita_debronze.repository.ComandaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ComandaServiceImpl implements ComandaService {

    private final ComandaRepository repository;
    private final AgendamentoRepository agendamentoRepository;

    public ComandaServiceImpl(ComandaRepository repository, AgendamentoRepository agendamentoRepository) {
        this.repository = repository;
        this.agendamentoRepository = agendamentoRepository;
    }

    @Override
    public Comanda salvarComanda(Comanda comanda) {
        // --- LOGS PARA DEPURAÇÃO ---
        System.out.println("--- Iniciando salvarComanda ---");
        if (comanda.getAgendamento() != null) {
            System.out.println("Agendamento ID recebido: " + comanda.getAgendamento().getId());
        } else {
            System.out.println("Agendamento recebido é NULL.");
        }
        System.out.println("Número de itens recebidos: " + (comanda.getItens() != null ? comanda.getItens().size() : "NULL"));
        // --- FIM DOS LOGS ---

        // 1. Vincular Agendamento e preencher dados automáticos
        if (comanda.getAgendamento() != null && comanda.getAgendamento().getId() != null) {
            Agendamento agendamento = agendamentoRepository.findById(comanda.getAgendamento().getId())
                    .orElseThrow(() -> new RuntimeException("Agendamento não encontrado com ID: " + comanda.getAgendamento().getId()));
            
            comanda.setAgendamento(agendamento);
            
            if (agendamento.getCliente() != null) {
                comanda.setClienteNome(agendamento.getCliente().getNome());
            }
        }

        // 2. Definir data atual se não vier preenchida
        if (comanda.getData() == null) {
            comanda.setData(LocalDateTime.now());
        }

        // 3. Calcular Valor Total
        calcularValorTotal(comanda);
        System.out.println("Valor total calculado: " + comanda.getValorTotal());

        Comanda comandaSalva = repository.save(comanda);
        System.out.println("Comanda salva com ID: " + comandaSalva.getId() + " e Agendamento ID: " + (comandaSalva.getAgendamento() != null ? comandaSalva.getAgendamento().getId() : "NULL"));
        System.out.println("--- Finalizando salvarComanda ---");
        return comandaSalva;
    }

    private void calcularValorTotal(Comanda comanda) {
        double total = 0.0;
        if (comanda.getItens() != null) {
            for (ItemComanda item : comanda.getItens()) {
                if (item.getPrecoUnitario() != null && item.getQuantidade() != null) {
                    total += item.getPrecoUnitario() * item.getQuantidade();
                }
            }
        }
        comanda.setValorTotal(total);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comanda> listarTodas() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comanda> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Comanda> atualizarComanda(Long id, Comanda comandaAtualizada) {
        return repository.findById(id).map(existing -> {
            existing.setFormaPagamento(comandaAtualizada.getFormaPagamento());
            existing.setFechada(comandaAtualizada.getFechada());
            
            if (comandaAtualizada.getItens() != null) {
                existing.getItens().clear();
                existing.getItens().addAll(comandaAtualizada.getItens());
            }

            calcularValorTotal(existing);

            return repository.save(existing);
        });
    }

    @Override
    public boolean excluirComanda(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
