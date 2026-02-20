package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.entity.Unidade;
import com.feita_debronze.feita_debronze.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeServiceImpl implements UnidadeService {

    private final UnidadeRepository unidadeRepository;

    public UnidadeServiceImpl(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    @Override
    public Unidade salvarUnidade(Unidade unidade) {
        return unidadeRepository.save(unidade);
    }

    @Override
    public Unidade atualizarUnidade(Long id, Unidade unidadeAtualizada) {
        return unidadeRepository.findById(id)
                .map(unidade -> {
                    unidade.setNome(unidadeAtualizada.getNome());
                    unidade.setEndereco(unidadeAtualizada.getEndereco());
                    unidade.setTelefone(unidadeAtualizada.getTelefone());
                    return unidadeRepository.save(unidade);
                })
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada com id " + id));
    }

    @Override
    public List<Unidade> listarUnidades() {
        return unidadeRepository.findAll();
    }

    @Override
    public Optional<Unidade> buscarUnidadePorId(Long id) {
        return unidadeRepository.findById(id);
    }

    @Override
    public void deletarUnidade(Long id) {
        unidadeRepository.deleteById(id);
    }
}
