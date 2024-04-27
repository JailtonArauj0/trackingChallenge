package com.challenge.TrackingChallenge.domain.Cliente;

public enum TipoPessoa {
    FISICA("PF"),
    JURIDICA("PJ");

    private String tipo;

    TipoPessoa(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
