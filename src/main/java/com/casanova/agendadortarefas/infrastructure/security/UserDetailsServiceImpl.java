package com.casanova.agendadortarefas.infrastructure.security;

import com.casanova.agendadortarefas.business.dto.UsuarioDTO;
import com.casanova.agendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient client;

    public UserDetails carregaDadosUsuario(String mail, String token) {
        UsuarioDTO usuarioDTO = client.buscaUsuarioPorEmail(mail, token);

        // SEGURANÇA: Passamos password("") porque o usuário já foi validado via assinatura JWT.
        // Isso permite que o microsserviço 'usuario' oculte o hash da senha na resposta JSON
        // sem causar IllegalArgumentException no construtor do UserDetails.
        return User
                .withUsername(usuarioDTO.getEmail())
                .password("")
                .roles("USER")
                .build();
    }
}
