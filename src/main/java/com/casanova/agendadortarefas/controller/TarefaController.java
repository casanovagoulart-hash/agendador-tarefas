package com.casanova.agendadortarefas.controller;

import com.casanova.agendadortarefas.business.TarefaService;
import com.casanova.agendadortarefas.business.dto.TarefaDTO;
import com.casanova.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> gravaTarefa(@RequestBody TarefaDTO dto,
                                                 @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.gravaTarefa(token, dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> buscaTarefasAgendadasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscaTarefasAgendadasPorUsuario(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorUsuario(token));
    }

    // NOVO ENDPOINT: Adicionado para atender à chamada buscaTarefaPorEmail do BFF
    @GetMapping("/email")
    public ResponseEntity<List<TarefaDTO>> buscaTarefaPorEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(tarefaService.buscaTarefaPorEmail(email));
    }

    // CORREÇÃO REST: Alterado de @RequestParam para @PathVariable("/{id}") para alinhar com o BFF
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaTarefaPorId(@PathVariable("id") String id) {
        tarefaService.deletaTarefaPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TarefaDTO> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                             @PathVariable("id") String id) {
        return ResponseEntity.ok(tarefaService.alteraStatus(status, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> atualizaTarefa(@RequestBody TarefaDTO dto,
                                                    @PathVariable("id") String id) {
        return ResponseEntity.ok(tarefaService.atualizaTarefa(dto, id));
    }
}
