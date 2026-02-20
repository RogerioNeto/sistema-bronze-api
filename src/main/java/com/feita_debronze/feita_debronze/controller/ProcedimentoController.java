package com.feita_debronze.feita_debronze.controller;

import com.feita_debronze.feita_debronze.entity.Procedimento;
import com.feita_debronze.feita_debronze.service.ProcedimentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procedimentos")
public class ProcedimentoController {

    private final ProcedimentoService procedimentoService;

    public ProcedimentoController(ProcedimentoService procedimentoService) {
        this.procedimentoService = procedimentoService;
    }

    @PostMapping
    public ResponseEntity<Procedimento> criarProcedimento(@RequestBody Procedimento procedimento) {
        Procedimento novoProcedimento = procedimentoService.salvarProcedimento(procedimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProcedimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Procedimento> atualizarProcedimento(@PathVariable Long id, @RequestBody Procedimento procedimento) {
        Procedimento procedimentoAtualizado = procedimentoService.atualizarProcedimento(id, procedimento);
        return ResponseEntity.ok(procedimentoAtualizado);
    }

    @GetMapping
    public ResponseEntity<List<Procedimento>> listarProcedimentos() {
        return ResponseEntity.ok(procedimentoService.listarProcedimentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Procedimento> buscarProcedimento(@PathVariable Long id) {
        return procedimentoService.buscarProcedimentoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProcedimento(@PathVariable Long id) {
        procedimentoService.deletarProcedimento(id);
        return ResponseEntity.noContent().build();
    }
}
