package com.casanova.agendadortarefas.infrastructure.entitys;

import com.casanova.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("tarefas")
public class TarefasEntity {

    @Id
    private String id;
    private String nomeTarefa;

    // CORREÇÃO: Alinhado com o DTO ('descricao' e 'dataAlteracao') para o MapStruct mapear automaticamente
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;

    public void setStatusNotificacao(StatusNotificacaoEnum status) {
        this.statusNotificacaoEnum = status;
    }
}
