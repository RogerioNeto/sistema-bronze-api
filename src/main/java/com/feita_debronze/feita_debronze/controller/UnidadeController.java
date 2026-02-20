package com.feita_debronze.feita_debronze.controller;

import com.feita_debronze.feita_debronze.entity.Unidade;
import com.feita_debronze.feita_debronze.service.UnidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidades")
public class UnidadeController {

    private final UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @PostMapping
    public ResponseEntity<Unidade> criarUnidade(@RequestBody Unidade unidade) {
        Unidade novaUnidade = unidadeService.salvarUnidade(unidade);
        return new ResponseEntity<>(novaUnidade, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unidade> atualizarUnidade(@PathVariable Long id, @RequestBody Unidade unidade) {
        Unidade unidadeAtualizada = unidadeService.atualizarUnidade(id, unidade);
        return ResponseEntity.ok(unidadeAtualizada);
    }

    @GetMapping
    public ResponseEntity<List<Unidade>> listarUnidades() {
        return ResponseEntity.ok(unidadeService.listarUnidades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidade> buscarUnidade(@PathVariable Long id) {
        return unidadeService.buscarUnidadePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUnidade(@PathVariable Long id) {
        unidadeService.deletarUnidade(id);
        return ResponseEntity.noContent().build();
    }
}
