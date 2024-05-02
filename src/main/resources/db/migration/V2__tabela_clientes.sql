CREATE TABLE tb_cliente
(
    id              BIGINT       NOT NULL AUTO_INCREMENT,
    nome            VARCHAR(255) NOT NULL,
    endereco        VARCHAR(255) NOT NULL,
    telefone        VARCHAR(11)  NOT NULL,
    email           VARCHAR(255) NOT NULL,
    tipo_pessoa     VARCHAR(10)  NOT NULL,
    cpf             VARCHAR(11) UNIQUE,
    sexo            VARCHAR(20),
    cnpj            VARCHAR(14) UNIQUE,
    data_nascimento DATE,
    razao_social    VARCHAR(255),
    data_inscricao  DATE,
    primary key (id)
)