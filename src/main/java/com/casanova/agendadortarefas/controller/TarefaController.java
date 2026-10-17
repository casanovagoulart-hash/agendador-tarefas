package com.casanova.agendadortarefas.controller;


import com.casanova.agendadortarefas.business.TarefaService;
import com.casanova.agendadortarefas.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
