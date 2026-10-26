package com.casanova.agendadortarefas.business.mapper;


import com.casanova.agendadortarefas.business.dto.TarefaDTO;
import com.casanova.agendadortarefas.infrastructure.entitys.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateMapper {
    void updateTarefa(TarefaDTO dto, @MappingTarget TarefasEntity entity);

}
