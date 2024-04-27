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
@DiscriminatorValue("PF")
public class ClienteFisica extends Cliente {
    @NotNull
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    private String sexo;

    @NotNull
    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
}
