package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.entity.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente salvarCliente(Cliente cliente);
    Cliente atualizarCliente(Long id, Cliente cliente);
    List<Cliente> listarClientes();
    Optional<Cliente> buscarClientePorId(Long id);
    void deletarCliente(Long id);
}
