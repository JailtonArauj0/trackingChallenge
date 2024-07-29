package com.challenge.TrackingChallenge.domain.Veiculo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record VeiculoDTO(
        long id,
        @NotNull
        String marca,
        @NotNull
        String modelo,
        @NotNull
        String cor,
        @NotNull
        String tipo,
        @NotNull
        String combustivel,
        @NotNull
        String anoFabricacao,

        @Size(min = 7, max = 7)
        @NotNull
        String placa,

        @Size(min = 11, max = 11)
        @NotNull
        String renavam,

        @Size(min = 17, max = 19)
        @NotNull
        String chassi,
        @NotNull
        Long idCliente
) {

    public VeiculoDTO(Veiculo veiculo) {
        this(
                veiculo.getId(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getCor(),
                veiculo.getTipo(),
                veiculo.getCombustivel(),
                veiculo.getAnoFabricacao(),
                veiculo.getPlaca(),
                veiculo.getRenavam(),
                veiculo.getChassi(),
                veiculo.getIdCliente()
        );
    }
}
