package com.challenge.TrackingChallenge.controllers;

import com.challenge.TrackingChallenge.config.security.TokenService;
import com.challenge.TrackingChallenge.domain.Usuario.LoginDTO;
import com.challenge.TrackingChallenge.domain.Usuario.Usuario;
import com.challenge.TrackingChallenge.domain.Usuario.UsuarioDTO;
import com.challenge.TrackingChallenge.domain.Usuario.UsuarioRegistroDTO;
import com.challenge.TrackingChallenge.exception.CustomException;
import com.challenge.TrackingChallenge.repositories.UsuarioRepository;
import com.challenge.TrackingChallenge.utils.ResponsePadrao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
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

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ResponsePadrao responsePadrao;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioDTO usuarioDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(usuarioDTO.login(), usuarioDTO.senha());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return new ResponseEntity(new LoginDTO(token), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<ResponsePadrao> registrar(@RequestBody @Valid UsuarioRegistroDTO usuarioRegistroDTO){
        if(usuarioRepository.listarUsuario(usuarioRegistroDTO.login()) != null){
            throw new CustomException("Já existe um usuario com o login informado.");
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioRegistroDTO.senha());
        Usuario usuario = new Usuario(usuarioRegistroDTO.login(), senhaCriptografada, usuarioRegistroDTO.role());

        Usuario usuarioCadastrado = usuarioRepository.salvarUsuario(usuario);

        if (usuarioCadastrado != null){
            responsePadrao.setMensagem("Usuário registrado com sucesso.");
            responsePadrao.setStatus(HttpStatus.CREATED.value());
            return new ResponseEntity<>(responsePadrao, HttpStatus.CREATED);

        }else{
            responsePadrao.setMensagem("Erro ao registrar usuário.");
            responsePadrao.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(responsePadrao, HttpStatus.BAD_REQUEST);
        }
    }
}
