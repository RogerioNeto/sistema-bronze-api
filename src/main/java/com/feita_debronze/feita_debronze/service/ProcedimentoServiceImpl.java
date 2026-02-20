package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.entity.Procedimento;
import com.feita_debronze.feita_debronze.repository.ProcedimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcedimentoServiceImpl implements ProcedimentoService {

    private final ProcedimentoRepository procedimentoRepository;

    public ProcedimentoServiceImpl(ProcedimentoRepository procedimentoRepository) {
        this.procedimentoRepository = procedimentoRepository;
    }

    @Override
    public Procedimento salvarProcedimento(Procedimento procedimento) {
        return procedimentoRepository.save(procedimento);
    }

    @Override
    public Procedimento atualizarProcedimento(Long id, Procedimento procedimentoAtualizado) {
        return procedimentoRepository.findById(id)
                .map(procedimento -> {
                    procedimento.setNome(procedimentoAtualizado.getNome());
                    procedimento.setValor(procedimentoAtualizado.getValor());
                    procedimento.setDuracao(procedimentoAtualizado.getDuracao());
                    return procedimentoRepository.save(procedimento);
                })
                .orElseThrow(() -> new RuntimeException("Procedimento não encontrado com id " + id));
    }

    @Override
    public List<Procedimento> listarProcedimentos() {
        return procedimentoRepository.findAll();
    }

    @Override
    public Optional<Procedimento> buscarProcedimentoPorId(Long id) {
        return procedimentoRepository.findById(id);
    }

    @Override
    public void deletarProcedimento(Long id) {
        procedimentoRepository.deleteById(id);
    }
}
