package com.feita_debronze.feita_debronze.service;

import com.feita_debronze.feita_debronze.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario salvarUsuario(Usuario usuario);
    Usuario atualizarUsuario(Long id, Usuario usuario);
    List<Usuario> listarUsuarios();
    Optional<Usuario> buscarUsuarioPorId(Long id);
    void deletarUsuario(Long id);
}
