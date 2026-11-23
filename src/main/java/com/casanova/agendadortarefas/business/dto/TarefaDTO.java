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

    // CORREÇÃO: Padronizado para 'descricao' para espelhar exatamente o JSON enviado pelo BFF
    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;

    // CORREÇÃO: Removido @JsonFormat deste campo (String de e-mail não é data)
    private String emailUsuario;

    // CORREÇÃO: Ajustado de 'dataAteracao' para 'dataAlteracao' e aplicada máscara ISO/BR
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAlteracao;

    // CORREÇÃO: Removido @JsonFormat deste campo (Enum já serializa pelo nome)
    private StatusNotificacaoEnum statusNotificacaoEnum;

    public void setEmail(String email) {
        this.emailUsuario = email;
    }
}
