# Documentação do Projeto

## 1. Introdução

Este projeto é uma prática aplicação para gerenciar usuários e departamentos. Ele foi desenvolvido para realizar operações fundamentais de CRUD para essas informações. O projeto segue boas práticas de programação com base no clean code e utiliza tecnologias atuais que estou trabalhando e estudando no momento.

### Funcionalidades Principais

- Listagem de usuários e departamentos
- Criação, edição e exclusão de usuários e departamentos
- Relacionamento entre usuários e departamentos

## 2. Visão Geral

### Pré-requisitos

- Java 8 ou superior
- Spring Boot
- Banco de dados compatível com JPA (por exemplo, MySQL, PostgreSQL)
- Docker (não utilizado)

### Arquitetura

A aplicação segue uma arquitetura de camadas simples:

- **Controller**: Lida com requisições HTTP e respostas.
- **Service**: Implementa a lógica de negócios.
- **Repository**: Gerencia a interação com o banco de dados.

## 3. Configuração e Variáveis de Ambiente

### Configuração do Banco de Dados H2
    environment:
      SPRING_DATASOURCE_URL: jdbc:h2:mem:testdb
      SPRING_DATASOURCE_USERNAME: gab
      SPRING_DATASOURCE_PASSWORD: 741
      SPRING_PROFILES_ACTIVE: h2
      SPRING_H2_CONSOLE_ENABLED: true
