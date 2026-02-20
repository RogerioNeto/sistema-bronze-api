package com.feita_debronze.feita_debronze.entity;

import com.feita_debronze.feita_debronze.enumeration.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Caso o usuário seja uma Responsável, ela pertence a uma unidade
    @ManyToOne
    @JoinColumn(name = "unidade_id")
    private Unidade unidade;

}
