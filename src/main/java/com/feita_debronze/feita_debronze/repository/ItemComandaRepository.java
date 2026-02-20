package com.feita_debronze.feita_debronze.repository;

import com.feita_debronze.feita_debronze.entity.ItemComanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemComandaRepository extends JpaRepository<ItemComanda, Long> {
}
