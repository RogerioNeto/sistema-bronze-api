package com.feita_debronze.feita_debronze.controller;

import com.feita_debronze.feita_debronze.dto.AgendamentoRequestDTO;
import com.feita_debronze.feita_debronze.entity.Agendamento;
import com.feita_debronze.feita_debronze.service.AgendamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping({"", "/pre-reserva"})
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody AgendamentoRequestDTO agendamentoDTO) {
        Agendamento novoAgendamento = agendamentoService.salvarAgendamento(agendamentoDTO);
        return new ResponseEntity<>(novoAgendamento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable Long id, @RequestBody AgendamentoRequestDTO agendamentoDTO) {
        Agendamento agendamentoAtualizado = agendamentoService.atualizarAgendamento(id, agendamentoDTO);
        return ResponseEntity.ok(agendamentoAtualizado);
    }

    @PutMapping("/{id}/aprovar")
    public ResponseEntity<Agendamento> aprovarAgendamento(@PathVariable Long id) {
        Agendamento agendamentoAprovado = agendamentoService.aprovarAgendamento(id);
        return ResponseEntity.ok(agendamentoAprovado);
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> listarAgendamentos() {
        return ResponseEntity.ok(agendamentoService.listarAgendamentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarAgendamento(@PathVariable Long id) {
        return agendamentoService.buscarAgendamentoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id) {
        agendamentoService.deletarAgendamento(id);
        return ResponseEntity.noContent().build();
    }
}
