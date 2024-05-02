CREATE TABLE tb_usuarios
(
    id    BIGINT       NOT NULL AUTO_INCREMENT,
    login varchar(50)  NOT NULL UNIQUE,
    senha varchar(255) NOT NULL,
    ROLE  varchar(20)  NOT NULL,
    PRIMARY KEY (id)
)