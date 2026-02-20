package com.feita_debronze.feita_debronze.config;

import com.feita_debronze.feita_debronze.entity.Usuario;
import com.feita_debronze.feita_debronze.enumeration.Role;
import com.feita_debronze.feita_debronze.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Criar usuário ADMIN se não existir
            Optional<Usuario> adminOptional = usuarioRepository.findByEmail("admin@feitadebronze.com");
            if (adminOptional.isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNome("Administrador");
                admin.setEmail("admin@feitadebronze.com");
                admin.setSenha(passwordEncoder.encode("admin123")); // Senha criptografada
                admin.setRole(Role.ADMIN);
                usuarioRepository.save(admin);
                System.out.println("Usuário ADMIN criado: admin@feitadebronze.com / admin123");
            }

            // Criar usuário RESPONSAVEL se não existir
            Optional<Usuario> responsavelOptional = usuarioRepository.findByEmail("responsavel@feitadebronze.com");
            if (responsavelOptional.isEmpty()) {
                Usuario responsavel = new Usuario();
                responsavel.setNome("Responsável Unidade");
                responsavel.setEmail("responsavel@feitadebronze.com");
                responsavel.setSenha(passwordEncoder.encode("resp123")); // Senha criptografada
                responsavel.setRole(Role.RESPONSAVEL);
                usuarioRepository.save(responsavel);
                System.out.println("Usuário RESPONSAVEL criado: responsavel@feitadebronze.com / resp123");
            }
        };
    }
}
