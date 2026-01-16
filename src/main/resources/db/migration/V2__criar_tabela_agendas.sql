CREATE TABLE agendas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    profissional_id BIGINT NOT NULL,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    status VARCHAR(30) NOT NULL,

    CONSTRAINT fk_agenda_profissional
        FOREIGN KEY (profissional_id)
        REFERENCES profissionais_saude(id)
);
