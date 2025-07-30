package com.devotion.ztita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devotion.ztita.model.Usuario;
import com.devotion.ztita.repository.UsuarioRepository;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listaUsuarios() {
        return usuarioRepository.findAll();
    }
	
	
	
}
