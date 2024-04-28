package com.challenge.TrackingChallenge.domain.Cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ClienteDTO(
        long id,
        @NotNull
        String nome,
        @Size(min = 8, max = 9, message = "O sexo deve ser Masculino ou Feminino.")
        String sexo,

        @NotNull
        @Email
        String email,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataInscricao,

        @Size(min = 11, max = 11)
        String telefone,
        String endereco,
        String cpf,
        String cnpj,
        String razaoSocial,
        String nomeFantasia,

        @NotNull
        TipoPessoa tipoPessoa) {
}
