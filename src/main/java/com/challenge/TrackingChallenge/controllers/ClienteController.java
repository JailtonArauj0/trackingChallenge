package com.challenge.TrackingChallenge.controllers;

import com.challenge.TrackingChallenge.domain.Cliente.Cliente;
import com.challenge.TrackingChallenge.domain.Cliente.ClienteDTO;
import com.challenge.TrackingChallenge.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        return new ResponseEntity<>(clienteService.cadastrarCliente(clienteDTO), HttpStatus.CREATED);
    }

    @GetMapping("/buscarPorCpf")
    public ResponseEntity<Cliente> listarPorCpf(@RequestParam String cpf) {
        // todo criar função de validar e fazer a validação antes da busca
        Cliente cliente = clienteService.listarPorCpf(cpf);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/buscarPorCnpj")
    public ResponseEntity<Cliente> listarPorCnpj(@RequestParam String cnpj) {
        // todo criar função de validar e fazer a validação antes da busca
        Cliente cliente = clienteService.listarPorCnpj(cnpj);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
