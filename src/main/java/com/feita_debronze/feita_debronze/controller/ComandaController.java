package com.feita_debronze.feita_debronze.controller;

import com.feita_debronze.feita_debronze.entity.Comanda;
import com.feita_debronze.feita_debronze.service.ComandaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comandas")
public class ComandaController {

    private final ComandaService comandaService;

    public ComandaController(ComandaService comandaService) {
        this.comandaService = comandaService;
    }

    @PostMapping
    public ResponseEntity<Comanda> criarComanda(@RequestBody Comanda comanda) {
        Comanda comandaSalva = comandaService.salvarComanda(comanda);
        return ResponseEntity.status(HttpStatus.CREATED).body(comandaSalva);
    }

    @GetMapping
    public ResponseEntity<List<Comanda>> listarTodas() {
        List<Comanda> comandas = comandaService.listarTodas();
        return ResponseEntity.ok(comandas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Comanda>> buscarPorId(@PathVariable Long id) {
        Optional<Comanda> comanda = comandaService.buscarPorId(id);
        return ResponseEntity.ok(comanda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Comanda>> atualizarComanda(@PathVariable Long id, @RequestBody Comanda comanda) {
        Optional<Comanda> comandaAtualizada = comandaService.atualizarComanda(id, comanda);
        return ResponseEntity.ok(comandaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComanda(@PathVariable Long id) {
        comandaService.excluirComanda(id);
        return ResponseEntity.noContent().build();
    }
}
