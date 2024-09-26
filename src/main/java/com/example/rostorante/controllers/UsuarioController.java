package com.example.rostorante.controllers;

import com.example.rostorante.model.Usuario;
import com.example.rostorante.repository.UsuarioRepository;
import com.example.rostorante.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioRepository.save(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/perfil")
    public ResponseEntity<Usuario> getPerfilUsuario(@RequestParam String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
