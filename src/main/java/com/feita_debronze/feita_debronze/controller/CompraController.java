package com.feita_debronze.feita_debronze.controller;

import com.feita_debronze.feita_debronze.entity.Compra;
import com.feita_debronze.feita_debronze.service.CompraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    public ResponseEntity<Compra> criarCompra(@RequestBody Compra compra) {
        Compra novaCompra = compraService.salvarCompra(compra);
        return new ResponseEntity<>(novaCompra, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> atualizarCompra(@PathVariable Long id, @RequestBody Compra compra) {
        Compra compraAtualizada = compraService.atualizarCompra(id, compra);
        return ResponseEntity.ok(compraAtualizada);
    }

    @GetMapping
    public ResponseEntity<List<Compra>> listarCompras() {
        return ResponseEntity.ok(compraService.listarCompras());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> buscarCompra(@PathVariable Long id) {
        return compraService.buscarCompraPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCompra(@PathVariable Long id) {
        compraService.deletarCompra(id);
        return ResponseEntity.noContent().build();
    }
}
