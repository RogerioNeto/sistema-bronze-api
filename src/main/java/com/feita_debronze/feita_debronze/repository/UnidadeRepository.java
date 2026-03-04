package com.feita_debronze.feita_debronze.repository;

import com.feita_debronze.feita_debronze.entity.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
    Optional<Unidade> findByNome(String nome); // Novo método

    Optional<Unidade> findById(Long id);

}
