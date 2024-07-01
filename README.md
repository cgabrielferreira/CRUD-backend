# Documentação do Projeto

## 1. Introdução

Este projeto é uma prática aplicação para gerenciar usuários e departamentos. Ele foi desenvolvido para realizar operações fundamentais de CRUD para essas informações. O projeto segue boas práticas de programação com base no clean code e utiliza tecnologias atuais que estou trabalhando e estudando no momento.

### Funcionalidades Principais

- Listagem de usuários e departamentos
- Criação, edição e exclusão de usuários e departamentos
- Relacionamento entre usuários e departamentos

## 2. Visão Geral

### Pré-requisitos

- Java 17
- Spring Boot
- Banco de dados compatível com JPA (por exemplo, MySQL, PostgreSQL)

### Arquitetura

A aplicação segue uma arquitetura de camadas simples:

- **Controller**: Lida com requisições HTTP e respostas.
- **Service**: Implementa a lógica de negócios.
- **Repository**: Gerencia a interação com o banco de dados.

### Testes

Foram adicionados os testes unitários com os seguintes frameworks: 

- **JUnit 5**
- **Mockito**

## 3. Configuração e Variáveis de Ambiente

### Configuração do Maven
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-resources-plugin</artifactId>
	<version>3.1.0</version>
</plugin>

### Collection Postman
https://www.postman.com/descent-module-operator-37573572/workspace/publicspace/collection/36580463-b6a470a2-74d0-4d48-83af-3c74d54fcbc2?action=share&creator=36580463


### Diagrama Relacional

![Diagrama](https://github.com/cgabrielferreira/UserDept/assets/87282150/d35550f6-9627-4ca6-b456-aabedbccebb0)


