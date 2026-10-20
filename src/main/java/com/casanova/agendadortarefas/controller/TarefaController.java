package com.casanova.agendadortarefas.controller;


import com.casanova.agendadortarefas.business.TarefaService;
import com.casanova.agendadortarefas.business.dto.TarefaDTO;
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
                                                 @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.gravaTarefa(token, dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> buscaTarefasAgendadasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal){
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscaTarefasAgendadasPorUsuario(@RequestHeader("Authorization") String token){
        List<TarefaDTO> tarefas = tarefaService.buscaTarefasAgendadasPorUsuario(token);
         return ResponseEntity.ok(tarefas);
    }
    @DeleteMapping
    public ResponseEntity<Void> deletaTarefa(@RequestParam Long id,
                                             @RequestHeader("Authorization") String token){
        tarefaService.deletaTarefa(id, token);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> atualizaTarefa(@RequestBody TarefaDTO dto,
                                                    @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.atualizaTarefa(token, dto));
    }
}
