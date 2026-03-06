package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.entity.Cliente;
import com.feita_debronze.feita_debronze.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) { this.clienteRepository = clienteRepository; }

    @Override
    public Cliente salvarCliente(Cliente cliente) {  return clienteRepository.save(cliente); }

    @Override
    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        return clienteRepository.findById(id)
                .map( cliente -> {

                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setApelido(clienteAtualizado.getApelido());
                    cliente.setEmail(clienteAtualizado.getEmail());
                    cliente.setTelefone(clienteAtualizado.getTelefone());
                    cliente.setObservacao(clienteAtualizado.getObservacao());
                    return clienteRepository.save(cliente);
                })
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id " + id));
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
    
    @Override
    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }


}
