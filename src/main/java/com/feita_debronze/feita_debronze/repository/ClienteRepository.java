package com.feita_debronze.feita_debronze.repository;

import org.springframework.stereotype.Repository;
import com.feita_debronze.feita_debronze.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
