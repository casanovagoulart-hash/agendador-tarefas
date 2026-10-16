package com.casanova.agendadortarefas.infrastructure.client;


import com.casanova.agendadortarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

// O retorno agora é UsuarioDTO (e não UsuarioService)
@GetMapping("/usuario")
UsuarioDTO buscaUsuarioPorEmail(@RequestParam("email") String email,
                                @RequestHeader("Authorization") String token);
}
