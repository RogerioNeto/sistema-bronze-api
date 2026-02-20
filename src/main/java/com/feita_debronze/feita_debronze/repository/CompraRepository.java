package com.feita_debronze.feita_debronze.repository;

import com.feita_debronze.feita_debronze.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}
