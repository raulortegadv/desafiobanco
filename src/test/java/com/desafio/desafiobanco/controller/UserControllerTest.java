package com.desafio.desafiobanco.controller;

import com.desafio.desafiobanco.model.Usuario;
import com.desafio.desafiobanco.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;
/* 
    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void getAllUsuarios() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/usuarios/findAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario[*].id").isNotEmpty());
    }

    @Test
    public void addUsuarioTest() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                        .post("/api/usuarios/addUsuario")
                        .content(asJsonString(new Usuario()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    */
}
