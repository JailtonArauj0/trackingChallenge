package com.challenge.TrackingChallenge.controllers;

import com.challenge.TrackingChallenge.domain.Veiculo.Veiculo;
import com.challenge.TrackingChallenge.domain.Veiculo.VeiculoDTO;
import com.challenge.TrackingChallenge.exception.CustomException;
import com.challenge.TrackingChallenge.services.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<Veiculo> cadastrar(@RequestBody @Valid VeiculoDTO veiculoDTO) {
        Veiculo veiculo = veiculoService.cadastrar(veiculoDTO);
        return new ResponseEntity<>(veiculo, HttpStatus.CREATED);
    }

    @GetMapping("/listarPorId")
    public ResponseEntity<VeiculoDTO> listarPorId(@RequestParam long id) {
        return new ResponseEntity<>(veiculoService.listarPorId(id), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/listarTodosPorIdCliente")
    public ResponseEntity<List<VeiculoDTO>> listarTodosPorIdCliente(@RequestParam long idCliente) {
        return new ResponseEntity<>(veiculoService.listarTodosPorIdCliente(idCliente), HttpStatus.OK);
    }

    @GetMapping("/listarPorParametros")
    public ResponseEntity<VeiculoDTO> listarPorParametros(
            @RequestParam(required = false, defaultValue = "") String placa,
            @RequestParam(required = false, defaultValue = "") String renavam,
            @RequestParam(required = false, defaultValue = "") String chassi
    ) {
        if (placa.isEmpty() && renavam.isEmpty() && chassi.isEmpty()) {
            throw new CustomException("Informe pelo menos um dos parâmetros: Placa, Renavam ou Chassi");
        }

        return new ResponseEntity<>(veiculoService.listarPorParametros(placa, renavam, chassi), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Veiculo> atualizar(@RequestBody @Valid VeiculoDTO veiculoDTO){
        return new ResponseEntity<>(veiculoService.atualizar(veiculoDTO), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping
    public ResponseEntity deletar(@RequestParam Long id){
        veiculoService.deletar(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
