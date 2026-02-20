package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.entity.Conta;
import com.feita_debronze.feita_debronze.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaServiceImpl implements ContaService {

    private final ContaRepository contaRepository;

    public ContaServiceImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public Conta salvarConta(Conta conta) {
        return contaRepository.save(conta);
    }

    @Override
    public Conta atualizarConta(Long id, Conta contaAtualizada) {
        return contaRepository.findById(id)
                .map(conta -> {
                    conta.setDescricao(contaAtualizada.getDescricao());
                    conta.setValor(contaAtualizada.getValor());
                    conta.setDataVencimento(contaAtualizada.getDataVencimento());
                    conta.setPaga(contaAtualizada.getPaga()); // Corrigido de setStatus para setPaga
                    // Removido o setUnidade que não existe
                    return contaRepository.save(conta);
                })
                .orElseThrow(() -> new RuntimeException("Conta não encontrada com id " + id));
    }

    @Override
    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    @Override
    public Optional<Conta> buscarContaPorId(Long id) {
        return contaRepository.findById(id);
    }

    @Override
    public void deletarConta(Long id) {
        contaRepository.deleteById(id);
    }
}
