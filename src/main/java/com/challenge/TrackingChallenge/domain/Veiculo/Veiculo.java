package com.challenge.TrackingChallenge.domain.Veiculo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String marca;

    @NotNull
    private String modelo;

    @NotNull
    private String cor;

    @NotNull
    private String tipo;

    @NotNull
    private String combustivel;

    @NotNull
    @Column(name = "ano_fabricacao")
    private String anoFabricacao;

    @Size(min = 7, max = 7)
    @NotNull
    private String placa;

    @Size(min = 11, max = 11)
    @NotNull
    private String renavam;

    @Size(min = 17, max = 19)
    @NotNull
    private String chassi;

    @NotNull
    @Column(name = "id_cliente")
    private long idCliente;
}
