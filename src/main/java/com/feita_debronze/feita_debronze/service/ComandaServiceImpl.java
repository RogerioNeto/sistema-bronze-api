package com.feita_debronze.feita_debronze.service.impl;

import com.feita_debronze.feita_debronze.entity.Comanda;
import com.feita_debronze.feita_debronze.repository.ComandaRepository;
import com.feita_debronze.feita_debronze.service.ComandaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ComandaServiceImpl implements ComandaService {

    private final ComandaRepository repository;

    public ComandaServiceImpl(ComandaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Comanda salvarComanda(Comanda comanda) {
        return repository.save(comanda);
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
    public Optional<Comanda> atualizarComanda(Long id, Comanda comanda) {
        return repository.findById(id).map(existing -> {
            BeanUtils.copyProperties(comanda, existing, "id");
            return repository.save(existing);
        });
    }

    @Override
    public boolean excluirComanda(Long id) {
        return repository.findById(id).map(c -> {
            repository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
