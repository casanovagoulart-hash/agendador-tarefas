package com.casanova.agendadortarefas.business.dto;

import com.casanova.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO {
    private String id;
    private String nomeTarefa;
    private String descrIcao;
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;

    public void setEmail(String email) {
        this.emailUsuario = email;
    }
}
