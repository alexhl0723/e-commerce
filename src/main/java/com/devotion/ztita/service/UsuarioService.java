package com.devotion.ztita.service;

import com.devotion.ztita.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import com.devotion.ztita.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado " + email));

        List<SimpleGrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_"+usuario.getRole().getNombreRol().toUpperCase())
        );
        System.out.println("ROL USUARIO: " + usuario.getRole().getNombreRol());
        System.out.println("AUTHORITIES: " + authorities);


        System.out.println("email: " + usuario.getEmail());
        System.out.println("hash: " + usuario.getPassword());


        return new User(usuario.getEmail(), usuario.getPassword(),authorities );

    }


}
