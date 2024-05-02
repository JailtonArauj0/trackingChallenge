CREATE TABLE tb_veiculos
(
    id             BIGINT AUTO_INCREMENT,
    marca          VARCHAR(50) NOT NULL,
    modelo         VARCHAR(50) NOT NULL,
    cor            VARCHAR(25) NOT NULL,
    tipo           VARCHAR(25) NOT NULL,
    combustivel    VARCHAR(25) NOT NULL,
    ano_fabricacao VARCHAR(4)  NOT NULL,
    placa          VARCHAR(7)  NOT NULL UNIQUE,
    renavam        VARCHAR(11) NOT NULL UNIQUE,
    chassi         VARCHAR(19) NOT NULL UNIQUE,
    id_cliente     BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (id_cliente) REFERENCES tb_cliente (id)
)