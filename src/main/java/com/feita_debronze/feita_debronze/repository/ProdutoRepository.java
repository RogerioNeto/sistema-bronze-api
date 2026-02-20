package com.feita_debronze.feita_debronze.repository;

import com.feita_debronze.feita_debronze.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
