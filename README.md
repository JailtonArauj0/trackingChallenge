# API de Veículos e Clientes

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

Desafio técnico para uma posição de analista backend. 

As tecnologias utilizadas foram Java, Spring boot, MySQL e Flyway Migrations, além de Spring Security e JWT Token para autenticação e autorização.

A API consiste em um CRUD de Clientes e Veículos associando a um Cliente específico.

# Instalação

1. Clone o repositório:
```bash
git clone https://github.com/JailtonArauj0/trackingChallenge
```

2. Instale as dependências usando o maven
3. Instale o [MySQL](https://www.mysql.com/)
4. Crie um banco de dados e atualize as configurações dos arquivos hibernateConfig.xml e application.properties de acordo com seu ambiente:
```bash
  hibernateConfig.xml
<property name="url" value="jdbc:mysql://localhost:3306/seu_banco"/>
<property name="username" value="seu_usuario"/>
<property name="password" value="sua_senha"/>

  application.properties
spring.flyway.url=jdbc:mysql://localhost:3306/seu_banco
spring.flyway.user=seu_usuario
spring.flyway.password=sua_senha
spring.flyway.schemas=seu_banco
spring.flyway.locations=classpath:db/migration
```
5. Rode a aplicação.

# Endpoints

Os endpoints estão disponíveis em uma collection do postman na pasta raíz do projeto.

# Autenticação

Cargos disponíveis:
```bash
USER -> Cargo padrão para utilização básica da API.
ADMIN -> Cargo para administração e acesso de endpoints específicos. Ex.: delete
```

# DDLs das tabelas

As tabelas serão criadas automaticamente, não sendo necessário executar os DDLs manualmente, mas caso queira ver a estrutura das mesmas, elas estão disponíveis no caminho: */resources/db/migration 