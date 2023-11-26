package com.desafio.desafiobanco.service;

import com.desafio.desafiobanco.model.Usuario;
import com.desafio.desafiobanco.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario saveUsuario(Usuario usuario){
        usuario.setFechaCreacion(LocalDateTime.now());
        usuario.setUltimaActualizacion(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> findAllUsuarios(){
        return usuarioRepository.findAll();
    }

    @Override
    public void deleteUsuario(Long usuarioId){
        usuarioRepository.deleteById(usuarioId);
    }

    @Override
    public Optional<Usuario> findById(Long id){
        return usuarioRepository.findById(id);
    }

    @Transactional
    @Override
    public Usuario updateUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Override
    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public boolean existsById(Long id){
        return usuarioRepository.existsById(id);
    }
}
