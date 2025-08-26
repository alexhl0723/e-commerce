    package com.devotion.ztita.controller;

import com.devotion.ztita.dtos.AuthRequest;
import com.devotion.ztita.dtos.AuthResponse;
import com.devotion.ztita.dtos.RegisterRequest;
import com.devotion.ztita.model.Role;
import com.devotion.ztita.model.Usuario;
import com.devotion.ztita.repository.UsuarioRepository;
import com.devotion.ztita.security.JwtTokenUtil;
import com.devotion.ztita.service.UsuarioService;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtiene el ID del rol del usuario
        int roleId = usuario.getRole().getIdRol();

        // Devuelve el token y el ID del rol
        return ResponseEntity.ok(new AuthResponse(jwt, roleId));
    }

    @PostMapping("/registrarse")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("⚠️ El correo ya está registrado.");
        }

        Usuario nuevo = new Usuario();
        nuevo.setNombres(request.getNombres());
        nuevo.setApellidoPaterno(request.getApellidoPaterno());
        nuevo.setApellidoMaterno(request.getApellidoMaterno());
        nuevo.setEmail(request.getEmail());
        nuevo.setPassword(passwordEncoder.encode(request.getPassword())); // 🔐 Encripta la contraseña
        nuevo.setTelefono(request.getTelefono());
        nuevo.setDireccion(request.getDireccion());
        nuevo.setFechaRegistro(new Date());

        // Asignar rol por defecto (ej: cliente con id_rol = 2)
        Role rolCliente = new Role();
        rolCliente.setIdRol(2); // Asegúrate que ese ID existe
        nuevo.setRole(rolCliente);

        usuarioRepository.save(nuevo);

       return ResponseEntity.ok(Map.of("message", "✅ Usuario registrado correctamente"));
    }


}
