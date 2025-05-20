package com.registro2.CRUD.service;

import com.registro2.CRUD.model.Usuario;
import com.registro2.CRUD.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol())));
    }
}
