package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.entity.Compra;
import com.feita_debronze.feita_debronze.repository.CompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;

    public CompraServiceImpl(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @Override
    public Compra salvarCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    @Override
    public Compra atualizarCompra(Long id, Compra compraAtualizada) {
        return compraRepository.findById(id)
                .map(compra -> {
                    compra.setDataCompra(compraAtualizada.getDataCompra());
                    compra.setValor(compraAtualizada.getValor());
                    compra.setFornecedor(compraAtualizada.getFornecedor());
                    compra.setDescricao(compraAtualizada.getDescricao());
                    return compraRepository.save(compra);
                })
                .orElseThrow(() -> new RuntimeException("Compra não encontrada com id " + id));
    }

    @Override
    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }

    @Override
    public Optional<Compra> buscarCompraPorId(Long id) {
        return compraRepository.findById(id);
    }

    @Override
    public void deletarCompra(Long id) {
        compraRepository.deleteById(id);
    }
}
