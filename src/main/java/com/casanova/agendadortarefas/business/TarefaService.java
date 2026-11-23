package com.casanova.agendadortarefas.business;

import com.casanova.agendadortarefas.business.dto.TarefaDTO;
import com.casanova.agendadortarefas.business.mapper.TarefaMapper;
import com.casanova.agendadortarefas.business.mapper.TarefaUpdateMapper;
import com.casanova.agendadortarefas.infrastructure.entitys.TarefasEntity;
import com.casanova.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.casanova.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
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
    private final TarefaUpdateMapper tarefaUpdateMapper;

    public TarefaDTO gravaTarefa(String token, TarefaDTO dto) {
        String email = jwtUtil.extractEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmail(email);

        TarefasEntity entity = tarefaMapper.paraTarefasEntity(dto);
        return tarefaMapper.paraTarefaDTO(tarefaRepository.save(entity));
    }

    public List<TarefaDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaMapper.paraListaTarefaDTO(
                tarefaRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(
                        dataInicial, dataFinal, StatusNotificacaoEnum.PENDENTE));
    }

    public List<TarefaDTO> buscaTarefasAgendadasPorUsuario(String token) {
        String email = jwtUtil.extractEmailToken(token.substring(7));
        // CORREÇÃO: Removida a consulta duplicada ao banco que descartava a primeira lista
        return tarefaMapper.paraListaTarefaDTO(tarefaRepository.findByEmailUsuario(email));
    }

    public List<TarefaDTO> buscaTarefaPorEmail(String email) {
        return tarefaMapper.paraListaTarefaDTO(tarefaRepository.findByEmailUsuario(email));
    }

    public void deletaTarefaPorId(String id) {
        // ARGUMENTO DIDÁTICO: O deleteById() padrão do MongoRepository não lança exceção se o ID
        // não existir. Verificar com existsById() garante que o cliente receba 404 Not Found.
        if (!tarefaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente: " + id);
        }
        tarefaRepository.deleteById(id);
    }

    public TarefaDTO alteraStatus(StatusNotificacaoEnum status, String id) {
        TarefasEntity entity = tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com o id: " + id));
        entity.setStatusNotificacaoEnum(status);
        entity.setDataAlteracao(LocalDateTime.now());
        return tarefaMapper.paraTarefaDTO(tarefaRepository.save(entity));
    }

    public TarefaDTO atualizaTarefa(TarefaDTO dto, String id) {
        TarefasEntity entity = tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com o id: " + id));
        tarefaUpdateMapper.updateTarefa(dto, entity);
        // CORREÇÃO: Registra automaticamente o timestamp de alteração
        entity.setDataAlteracao(LocalDateTime.now());
        return tarefaMapper.paraTarefaDTO(tarefaRepository.save(entity));
    }
}
