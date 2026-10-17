package com.casanova.agendadortarefas.infrastructure.entitys;

import com.casanova.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
* 1. Coleções de Documentos (O Molde dos Dados)
* São classes Java que representam a estrutura dos dados que serão salvos no banco.
• Imagine que você tem uma tabela de clientes; no Java, você cria a classe Cliente e
* usa anotações como @Document (para dizer que ela vai para o MongoDB) e @Id (para definir a chave única).
• Cada campo dessa classe vira uma informação dentro do documento no banco de dados.

* 2. Repositórios (As Ferramentas de Acesso)
* São interfaces que servem como uma "ponte" entre o seu código e o banco de dados.
• Ao criar uma interface que estende MongoRepository, o Spring cria automaticamente todas
* as funções necessárias para gerenciar os dados.
• Você ganha métodos prontos para usar imediatamente, como salvar (save()),
* buscar todos (findAll()), buscar por ID (findById()) e deletar (deleteById()),
* sem precisar escrever nenhuma linha de código de consulta.
*/

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
    private String descrIcao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;

}
