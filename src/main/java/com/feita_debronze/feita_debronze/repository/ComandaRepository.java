package com.feita_debronze.feita_debronze.repository;

import com.feita_debronze.feita_debronze.entity.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long> {
}
