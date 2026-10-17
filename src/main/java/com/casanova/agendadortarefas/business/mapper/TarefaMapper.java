package com.casanova.agendadortarefas.business.mapper;


import com.casanova.agendadortarefas.business.dto.TarefaDTO;
import com.casanova.agendadortarefas.infrastructure.entitys.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    TarefasEntity paraTarefasEntity(TarefaDTO dto);

    TarefaDTO paraTarefaDTO(TarefasEntity entity);
}
