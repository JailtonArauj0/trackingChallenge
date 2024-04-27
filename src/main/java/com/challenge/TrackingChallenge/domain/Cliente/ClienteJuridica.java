package com.challenge.TrackingChallenge.domain.Cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("PJ")
public class ClienteJuridica extends Cliente {
    @NotNull
    @Size(min = 14, max = 14)
    private String cnpj;

    @NotNull
    @Column(name = "razao_social")
    private String razaoSocial;

    @NotNull
    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "data_inscricao")
    private LocalDate dataInscricao;
}
