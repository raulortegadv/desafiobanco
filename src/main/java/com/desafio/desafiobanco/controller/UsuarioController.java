package com.desafio.desafiobanco.controller;

import com.desafio.desafiobanco.model.Mensaje;
import com.desafio.desafiobanco.model.Usuario;
import com.desafio.desafiobanco.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/addUsuario")
    public ResponseEntity<?> saveUsuario(@RequestBody @Valid Usuario usuario, Errors errors){
        if(errors.hasErrors()){
            log.error("Error con datos de usuario");
            return new ResponseEntity<>(new Mensaje("Error en tipos de datos"), HttpStatus.BAD_REQUEST);
        }
        if(usuarioService.existsByEmail(usuario.getEmail())) {
            log.error("Ya existe un usuario ese email");
            return new ResponseEntity(new Mensaje("Ya existe un usuario con ese email"), HttpStatus.BAD_REQUEST);
        }
        log.info("registro de usuario");
        return new ResponseEntity<>(usuarioService.saveUsuario(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllUsuarios(){
        return ResponseEntity.ok(usuarioService.findAllUsuarios());
    }

    @DeleteMapping("{usuarioId}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long usuarioId){
        if(!usuarioService.existsById(usuarioId)){
            return new ResponseEntity(new Mensaje("Usuario no existe"), HttpStatus.NOT_FOUND);
        }else {
            Usuario u = usuarioService.findById(usuarioId).get();
            u.setIsActive(0);
            usuarioService.deleteUsuario(u);
            log.info("Usuario Eliminado");
            return new ResponseEntity(new Mensaje("Usuario no eliminado"), HttpStatus.OK);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findByUsuarioId(@PathVariable Long id) {
        if(!usuarioService.existsById(id)){
            return new ResponseEntity(new Mensaje("Usuario no existe"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PutMapping("/actualizarUsuario/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable("id") Long id , @Valid @RequestBody Usuario usuario, Errors errors){
        if(!usuarioService.existsById(id)){
            return new ResponseEntity(new Mensaje("Usuario no existe"), HttpStatus.NOT_FOUND);
        }
        if(errors.hasErrors()){
            log.error("Error con datos de usuario");
            return new ResponseEntity<>(new Mensaje("Error en tipos de datos"),HttpStatus.BAD_REQUEST);
        }
        if(usuarioService.existsByEmail(usuario.getEmail())) {
            log.error("Ya existe un usuario ese email");
            return new ResponseEntity(new Mensaje("Ya existe un usuario con ese email"), HttpStatus.BAD_REQUEST);
        }
        log.info("Usuario actualizado");
        Usuario u = usuarioService.findById(id).get();
        u.setName(usuario.getName());
        u.setEmail(usuario.getEmail());
        u.setPassword(usuario.getPassword());
        u.setUltimaActualizacion(LocalDateTime.now());
        u.setTelefonos(usuario.getTelefonos());
        return new ResponseEntity(usuarioService.updateUsuario(u), HttpStatus.OK);
    }

}
