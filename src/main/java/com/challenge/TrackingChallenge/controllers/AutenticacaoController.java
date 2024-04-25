package com.challenge.TrackingChallenge.controllers;

import com.challenge.TrackingChallenge.domain.Usuario.Usuario;
import com.challenge.TrackingChallenge.domain.Usuario.UsuarioDTO;
import com.challenge.TrackingChallenge.domain.Usuario.UsuarioRegistroDTO;
import com.challenge.TrackingChallenge.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioDTO usuarioDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(usuarioDTO.login(), usuarioDTO.senha());
        var auth = authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody @Valid UsuarioRegistroDTO usuarioRegistroDTO){
        if(usuarioRepository.listarUsuario(usuarioRegistroDTO.login()) != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            //TODO retornar exceção customizada com mensagem detalhando o problema
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioRegistroDTO.senha());
        Usuario usuario = new Usuario(usuarioRegistroDTO.login(), senhaCriptografada, usuarioRegistroDTO.role());

        return new ResponseEntity<>(usuarioRepository.salvarUsuario(usuario), HttpStatus.CREATED);
    }
}
