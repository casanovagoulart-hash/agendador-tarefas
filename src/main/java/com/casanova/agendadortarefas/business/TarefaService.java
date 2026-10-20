package com.casanova.agendadortarefas.business;

import com.casanova.agendadortarefas.business.dto.TarefaDTO;
import com.casanova.agendadortarefas.business.mapper.TarefaMapper;
import com.casanova.agendadortarefas.infrastructure.entitys.TarefasEntity;
import com.casanova.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.casanova.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.casanova.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final JwtUtil jwtUtil;

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

    public void deletaTarefa(Long id, String token) {

    }
}
