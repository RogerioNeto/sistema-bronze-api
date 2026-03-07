package com.feita_debronze.feita_debronze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String telefone;

    private String apelido;

    @Column(unique = true)
    private String email;

    private String observacao;

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }
}
