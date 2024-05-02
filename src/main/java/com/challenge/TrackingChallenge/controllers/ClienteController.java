package com.challenge.TrackingChallenge.controllers;

import com.challenge.TrackingChallenge.domain.Cliente.Cliente;
import com.challenge.TrackingChallenge.domain.Cliente.ClienteDTO;
import com.challenge.TrackingChallenge.domain.Cliente.ClienteFisica;
import com.challenge.TrackingChallenge.domain.Cliente.ClienteJuridica;
import com.challenge.TrackingChallenge.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        return new ResponseEntity<>(clienteService.cadastrarCliente(clienteDTO), HttpStatus.CREATED);
    }

    @GetMapping("/listarPorId")
    public ResponseEntity<Cliente> listarPorCpf(@RequestParam long id) {
        Cliente cliente = clienteService.listarPorId(id);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/listarPorCpf")
    public ResponseEntity<Cliente> listarPorCpf(@RequestParam String cpf) {

        Cliente cliente = clienteService.listarPorCpf(cpf);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/listarPorCnpj")
    public ResponseEntity<Cliente> listarPorCnpj(@RequestParam String cnpj) {
        Cliente cliente = clienteService.listarPorCnpj(cnpj);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/listarTodos")
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> clientes = clienteService.listarTodos();
        if (clientes != null) {
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping
    public ResponseEntity<Cliente> atualizarCliente(@Valid @RequestBody ClienteDTO clienteDTO){
        if(clienteDTO.tipoPessoa().getTipo().equalsIgnoreCase("PF")){
            Cliente cliente = new ClienteFisica();
            BeanUtils.copyProperties(clienteDTO, cliente);
            Cliente clienteAtualizado = clienteService.atualizarCliente(cliente);
            if (clienteAtualizado != null) {
                return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Cliente cliente = new ClienteJuridica();
        BeanUtils.copyProperties(clienteDTO, cliente);
        Cliente clienteAtualizado = clienteService.atualizarCliente(cliente);
        if (clienteAtualizado != null) {
            return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping
    public ResponseEntity deletarCliente(@RequestParam Long id){
        clienteService.deletarCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
