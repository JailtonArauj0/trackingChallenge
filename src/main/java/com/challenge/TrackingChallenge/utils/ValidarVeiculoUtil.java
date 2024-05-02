package com.challenge.TrackingChallenge.utils;

import com.challenge.TrackingChallenge.domain.Cliente.Cliente;
import com.challenge.TrackingChallenge.domain.Veiculo.Veiculo;
import com.challenge.TrackingChallenge.exception.CustomException;
import com.challenge.TrackingChallenge.exception.EntityNotFoundException;
import com.challenge.TrackingChallenge.repositories.ClienteRepository;
import com.challenge.TrackingChallenge.repositories.VeiculoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidarVeiculoUtil {

    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;

    public boolean validarVeiculo(Veiculo veiculo) {
        Cliente clienteExiste = clienteRepository.listarPorId(veiculo.getIdCliente());
        Veiculo placaExiste = veiculoRepository.listarPorPlaca(veiculo.getPlaca());
        Veiculo renavamExiste = veiculoRepository.listarPorRenavam(veiculo.getRenavam());
        Veiculo chassiExiste = veiculoRepository.listarPorChassi(veiculo.getChassi());

        if (clienteExiste == null) {
            throw new EntityNotFoundException("Cliente não encontrado para o ID Cliente informado");
        } else if (placaExiste != null) {
            throw new CustomException("Esta placa já pertence a outro veículo, informe uma diferente");
        } else if (renavamExiste != null) {
            throw new CustomException("Este renavam já pertence a outro veículo, informe um diferente");
        } else if (chassiExiste != null) {
            throw new CustomException("Este chassi já pertence a outro veículo, informe um diferente");
        }
        return true;
    }

}
