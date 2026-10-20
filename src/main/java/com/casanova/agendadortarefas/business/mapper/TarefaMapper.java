package com.casanova.agendadortarefas.business.mapper;


import com.casanova.agendadortarefas.business.dto.TarefaDTO;
import com.casanova.agendadortarefas.infrastructure.entitys.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    @Mapping(source = "id", target ="id")
    @Mapping(source = "dataEvento", target ="dataEvento")
    @Mapping(source = "descrIcao", target ="descrIcao")
    TarefasEntity paraTarefasEntity(TarefaDTO dto);

    TarefaDTO paraTarefaDTO(TarefasEntity entity);

    List<TarefasEntity> paraListaTarefaEntity(List<TarefaDTO> dtos);

    List<TarefaDTO> paraListaTarefaDTO(List<TarefasEntity> entities);

}
