package com.desafio.desafiobanco.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotEmpty
    @NotNull
    private String name;

    @Column(name = "email" ,nullable = false)
    @Pattern(regexp="^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message="No se ha escrito en el formato permitido")
    @NotEmpty
    @NotNull
    private String email;

    @Column(name = "password", nullable = false)
    @Pattern(regexp="^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$")
    @NotEmpty
    @NotNull
    private String password;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;

    @Column(name = "telefonos", length = 1000, nullable = true)
    private Telefono[] telefonos;

    @Column(name = "isactive")
    private int isActive;

    @Column(name="token", length = 1000)
    private String token;
}
