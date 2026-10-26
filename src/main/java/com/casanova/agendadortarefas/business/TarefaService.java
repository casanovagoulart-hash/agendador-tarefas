package com.casanova.agendadortarefas.business;

import com.casanova.agendadortarefas.business.dto.TarefaDTO;
import com.casanova.agendadortarefas.business.mapper.TarefaMapper;
import com.casanova.agendadortarefas.business.mapper.TarefaUpdateMapper;
import com.casanova.agendadortarefas.infrastructure.entitys.TarefasEntity;
import com.casanova.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.casanova.agendadortarefas.infrastructure.exceptions.ResourseNotFoundException;
import com.casanova.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.casanova.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateMapper tarefaUpdateMapper;

public TarefaDTO gravaTarefa(String token, TarefaDTO dto) {
    String email = jwtUtil.extractEmailToken(token.substring(7));

    dto.setDataCriacao(LocalDateTime.now());
    dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
    dto.setEmail(email);
    TarefasEntity entity = tarefaMapper.paraTarefasEntity(dto);

    return tarefaMapper.paraTarefaDTO(
            tarefaRepository.save(entity));

}
public List<TarefaDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

    return tarefaMapper.paraListaTarefaDTO(
            tarefaRepository.findByDataEventoBetween(dataInicial, dataFinal));

}

public List<TarefaDTO> buscaTarefasAgendadasPorUsuario(String token) {
    String email = jwtUtil.extractEmailToken(token.substring(7));

    List<TarefasEntity> ListaTarefas = tarefaRepository.findByEmailUsuario(email);

    return tarefaMapper.paraListaTarefaDTO(
            tarefaRepository.findByEmailUsuario(email));

}

public TarefaDTO atualizaTarefa(String token, TarefaDTO dto) {
    String email = jwtUtil.extractEmailToken(token.substring(7));
    dto.setEmail(email);
    TarefasEntity entity = tarefaMapper.paraTarefasEntity(dto);

    return tarefaMapper.paraTarefaDTO(
           tarefaRepository.save(entity));
    }

public List<TarefaDTO> buscaTarefasUsuario(String token) {
    return tarefaMapper.paraListaTarefaDTO(
           tarefaRepository.findByEmailUsuario(
           jwtUtil.extractEmailToken(token.substring(7))));

    }

public void deletaTarefaPorId(String id) {
    try {
    tarefaRepository.deleteById(id);

  } catch (ResourseNotFoundException e) {

    throw new ResourseNotFoundException(
   "Erro ao deletar tarefa por id, id inexistente " + id, e.getCause());
  }

}

public TarefaDTO alteraStatus(StatusNotificacaoEnum status, String id) {
    try {
        TarefasEntity entity = tarefaRepository.findById(id).
                orElseThrow(() -> new ResourseNotFoundException("Tarefa não encontrada com o id: " + id));
        entity.setStatusNotificacaoEnum(status);
        return tarefaMapper.paraTarefaDTO(
                tarefaRepository.save(entity));

    } catch (ResourseNotFoundException e) {
        throw new ResourseNotFoundException(
                "Erro ao alterar status da tarefa, id inexistente " + id, e.getCause());
    }

}

public TarefaDTO atualizaTarefa(TarefaDTO dto, String id){
    try {
        TarefasEntity entity = tarefaRepository.findById(id).
                orElseThrow(() -> new ResourseNotFoundException("Tarefa não encontrada com o id: " + id));
        tarefaUpdateMapper.updateTarefa(dto, entity);

        return tarefaMapper.paraTarefaDTO(tarefaRepository.save(entity));

    } catch (ResourseNotFoundException e) {
        throw new ResourseNotFoundException(
                "Erro ao atualizar tarefa, id inexistente " + id, e.getCause());
    }

}

}
