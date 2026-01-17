-- =========================
-- USUÁRIOS (AUTH / SECURITY)
-- =========================
CREATE TABLE usuarios
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
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
    agenda_id       BIGINT,
    data_hora       TIMESTAMP   NOT NULL,
    tipo            VARCHAR(50) NOT NULL,
    status          VARCHAR(30) NOT NULL,

    CONSTRAINT fk_consulta_paciente
        FOREIGN KEY (paciente_id)
            REFERENCES pacientes (id),

    CONSTRAINT fk_consulta_profissional
        FOREIGN KEY (profissional_id)
            REFERENCES profissionais_saude (id),

    CONSTRAINT fk_consulta_agenda
        FOREIGN KEY (agenda_id)
            REFERENCES agendas (id)
);

CREATE TABLE prontuarios
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    consulta_id BIGINT NOT NULL,
    paciente_id BIGINT NOT NULL,
    profissional_id BIGINT NOT NULL,

    descricao TEXT NOT NULL,
    prescricao TEXT,

    data_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_prontuario_consulta
        FOREIGN KEY (consulta_id)
            REFERENCES consultas (id),

    CONSTRAINT fk_prontuario_paciente
        FOREIGN KEY (paciente_id)
            REFERENCES pacientes (id),

    CONSTRAINT fk_prontuario_profissional
        FOREIGN KEY (profissional_id)
            REFERENCES profissionais_saude (id)
);


-- =========================
-- AUDIT LOG
-- =========================
CREATE TABLE audit_logs
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,

    action     VARCHAR(50) NOT NULL,

    usuario_id BIGINT      NOT NULL,

    data_hora  TIMESTAMP   NOT NULL,

    detalhes   VARCHAR(500),

    CONSTRAINT fk_audit_usuario
        FOREIGN KEY (usuario_id)
            REFERENCES usuarios (id)
);
