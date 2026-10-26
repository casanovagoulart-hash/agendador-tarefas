package com.casanova.agendadortarefas.infrastructure.repository;

import com.casanova.agendadortarefas.infrastructure.entitys.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<TarefasEntity, String> {

    List<TarefasEntity> findByDataEventoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

    List<TarefasEntity> findByEmailUsuario(String emailUsuario);

    void deleteByIdAndEmailUsuario(Long id, String email);

    void deleteByEmailUsuario(String email);

    void deleteAllByEmailUsuario(String email);
}
