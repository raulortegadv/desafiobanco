package com.desafio.desafiobanco.service;

import com.desafio.desafiobanco.model.Usuario;
import com.desafio.desafiobanco.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario saveUsuario(Usuario usuario){
        String token = getJWTToken(usuario.getName());
        usuario.setFechaCreacion(LocalDateTime.now());
        usuario.setUltimaActualizacion(LocalDateTime.now());
        usuario.setToken(token);
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

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
