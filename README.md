# 🏥 SGHSS – Sistema de Gestão Hospitalar Simplificado

Projeto desenvolvido como trabalho final da disciplina **Desenvolvimento Back-End**, com o objetivo de aplicar conceitos de APIs REST, segurança, persistência de dados e boas práticas utilizando o ecossistema Java e Spring Boot.

---

## 📌 Objetivo do Projeto

O SGHSS tem como finalidade realizar o gerenciamento básico de um ambiente hospitalar, permitindo:

- Autenticação segura de usuários
- Cadastro e consulta de pacientes
- Gerenciamento de profissionais de saúde
- Agendamento, cancelamento e finalização de consultas
- Registro de prontuários médicos
- Auditoria de ações realizadas no sistema

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 4**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Spring Data JPA / Hibernate**
- **MySQL**
- **Lombok**
- **Insomnia** (testes de API)

---

## 🧱 Arquitetura do Projeto

O projeto segue uma arquitetura em camadas, separando responsabilidades de forma clara:

- **Controller** – Exposição dos endpoints REST
- **Service** – Regras de negócio
- **Repository** – Persistência de dados
- **Domain** – Entidades do sistema
- **DTO** – Objetos de transferência de dados
- **Security** – Configuração de autenticação e autorização
- **Audit** – Registro de ações no sistema

---

## 🔐 Segurança

A autenticação é realizada através de **JWT**, garantindo que apenas usuários autenticados possam acessar os endpoints protegidos.

- Login gera um token JWT
- O token deve ser enviado no header

  - Controle de acesso configurado via Spring Security

---

## 📂 Principais Rotas da API

### 🔑 Autenticação
- `POST /auth/login`  
Realiza o login e retorna o token JWT.

### 👤 Usuários
- `POST /usuarios`  
Cadastra novos usuários no sistema.

### 🧍 Pacientes
- `POST /pacientes`  
- `GET /pacientes`  
- `GET /pacientes/{id}`  

### 🩺 Profissionais de Saúde
- `POST /profissionais`
- `GET /profissionais`

### 📅 Consultas
- `POST /consultas`  
- `GET /consultas`  
- `PUT /consultas/{id}/cancelar`  
- `PUT /consultas/{id}/finalizar`

### 📄 Prontuários
- `POST /prontuarios`
- `GET /prontuarios/consulta/{consultaId}`

---

## 🧪 Testes

Os testes da API foram realizados manualmente utilizando o **Insomnia**, validando:

- Autenticação e autorização
- Fluxo completo de criação de consulta
- Finalização da consulta
- Criação e consulta de prontuário
- Restrições de acesso sem token

---

## 🗄️ Banco de Dados

O banco de dados utilizado é **MySQL**

## ▶️ Como Executar o Projeto

### Pré-requisitos
- Java 17
- MySQL
- Maven

### Passos
1. Clone o repositório:
   ```bash
   git clone https://github.com/mrlmurilo/Trabalho-Final-Back-End

2. Configure o banco no application.properties
3. Execute o projeto:
   ````bash
   mvn spring-boot:run

4. A API estará disponível em:
   ````bash
   http://localhost:8080
