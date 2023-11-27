package com.desafio.desafiobanco.service;

import com.desafio.desafiobanco.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario);

    List<Usuario> findAllUsuarios();

    Usuario deleteUsuario(Usuario usuario);

    Optional<Usuario> findById(Long id);

    Usuario updateUsuario(Usuario usuario);

    boolean existsByEmail(String email);

    boolean existsById(Long id);
}
