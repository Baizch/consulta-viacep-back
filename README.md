# API Spring Boot

Este projeto fornece uma API para busca de CEPs, utilizando Spring Boot para a construção de serviços RESTful.

## Tecnologias Utilizadas

- ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?logo=java&logoColor=white) Java 22
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white) Spring Boot 3.3.2
- ![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white) Docker

## Rodando Localmente

### Requisitos

- Java 22 instalado
- Maven instalado (ou outro gerenciador de dependências)

### Passos

1. **Clone o repositório**

    ```bash
    git clone git@github.com:Baizch/consulta-viacep-back.git
    
    cd consulta-viacep-back
    ```

2. **Compilar e Executar**

    - **Usando Maven**:

        ```bash
        mvn clean install
        
        mvn spring-boot:run
        ```

    - **Usando Gradle**:

        ```bash
        ./gradlew build
        
        ./gradlew bootRun
        ```

    A API estará disponível em `http://localhost:8080`.

## Rodando com Docker

### Requisitos

- Docker Desktop instalado

### Passos

1. **Construa a imagem Docker**

    ```bash
    docker-compose build
    ```

2. **Inicie a aplicação**

    ```bash
    docker-compose up
    ```

    A API estará disponível em `http://localhost:8080`.

## Endpoints

### Health Check

- **Endpoint**: `/api/health`
- **Método**: GET
- **Descrição**: Retorna o status de saúde da aplicação.

### Consulta de CEP

- **Endpoint**: `/api/cep/{cep}`
- **Método**: GET
- **Exemplo**: /api/cep/01001000
- **Descrição**: Retorna informações sobre um endereço.

Consulte a documentação para obter mais detalhes: 
