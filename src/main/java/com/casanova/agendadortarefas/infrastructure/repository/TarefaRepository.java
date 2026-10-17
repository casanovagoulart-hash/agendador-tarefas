package com.casanova.agendadortarefas.infrastructure.repository;

import com.casanova.agendadortarefas.infrastructure.entitys.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends MongoRepository<TarefasEntity, String> {


}
