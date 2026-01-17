-- =========================
-- USUÁRIOS (AUTH / SECURITY)
-- =========================
CREATE TABLE usuarios
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(50)  NOT NULL,
    enabled  BOOLEAN      NOT NULL DEFAULT TRUE
);

-- =========================
-- PACIENTES
-- =========================
CREATE TABLE pacientes
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(255) NOT NULL,
    cpf             VARCHAR(14)  NOT NULL UNIQUE,
    telefone        VARCHAR(20),
    email           VARCHAR(255),
    data_nascimento DATE
);

-- =========================
-- PROFISSIONAIS DE SAÚDE
-- =========================
CREATE TABLE profissionais_saude
(
    id                    BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome                  VARCHAR(255) NOT NULL,
    especialidade         VARCHAR(50)  NOT NULL,
    registro_profissional VARCHAR(50)  NOT NULL UNIQUE,
    telefone              VARCHAR(20),
    email                 VARCHAR(255)
);

-- =========================
-- AGENDAS
-- =========================
CREATE TABLE agendas
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    profissional_id BIGINT      NOT NULL,
    data            DATE        NOT NULL,
    hora            TIME        NOT NULL,
    status          VARCHAR(30) NOT NULL,

    CONSTRAINT fk_agenda_profissional
        FOREIGN KEY (profissional_id)
            REFERENCES profissionais_saude (id)
);

-- =========================
-- CONSULTAS
-- =========================
CREATE TABLE consultas
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id     BIGINT      NOT NULL,
    profissional_id BIGINT      NOT NULL,
    data_hora       TIMESTAMP   NOT NULL,
    tipo            VARCHAR(50) NOT NULL,
    status          VARCHAR(30) NOT NULL,

    CONSTRAINT fk_consulta_paciente
        FOREIGN KEY (paciente_id)
            REFERENCES pacientes (id),

    CONSTRAINT fk_consulta_profissional
        FOREIGN KEY (profissional_id)
            REFERENCES profissionais_saude (id)
);

-- =========================
-- AUDIT LOG
-- =========================
CREATE TABLE audit_logs
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,

    usuario     VARCHAR(100) NOT NULL,
    acao        VARCHAR(50)  NOT NULL,
    entidade    VARCHAR(100) NOT NULL,
    entidade_id BIGINT,

    data_hora   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    detalhes    VARCHAR(500)
);
