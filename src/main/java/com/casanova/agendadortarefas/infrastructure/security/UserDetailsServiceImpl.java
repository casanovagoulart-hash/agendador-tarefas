package com.casanova.agendadortarefas.infrastructure.security;

import com.casanova.agendadortarefas.business.dto.UsuarioDTO;
import com.casanova.agendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service // Apenas @Service: esta classe é uma regra de negócio, não um repositório
public class UserDetailsServiceImpl{

    @Autowired
    private UsuarioClient client;

    public UserDetails carregaDadosUsuario(String mail, String token){

        UsuarioDTO usuarioDTO= client.buscaUsuarioPorEmail(mail, token);
        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha (precisa estar criptografada com BCrypt no banco)
                .roles("USER") // Define o perfil de acesso; sem isso o usuário fica sem permissões
                .build();
    }

}