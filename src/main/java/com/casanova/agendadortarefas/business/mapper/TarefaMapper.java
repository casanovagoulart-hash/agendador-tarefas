package com.casanova.agendadortarefas.business.mapper;

import com.casanova.agendadortarefas.business.dto.TarefaDTO;
import com.casanova.agendadortarefas.infrastructure.entitys.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

// ARGUMENTO DIDÁTICO: Como os nomes dos atributos entre TarefaDTO e TarefasEntity agora são
// 100% idênticos (inclusive 'descricao' e 'dataAlteracao'), o MapStruct realiza o mapeamento
// automaticamente sem precisar de anotações @Mapping manuais.
@Mapper(componentModel = "spring")
public interface TarefaMapper {

    TarefasEntity paraTarefasEntity(TarefaDTO dto);

    TarefaDTO paraTarefaDTO(TarefasEntity entity);

    List<TarefasEntity> paraListaTarefaEntity(List<TarefaDTO> dtos);

    List<TarefaDTO> paraListaTarefaDTO(List<TarefasEntity> entities);
}
