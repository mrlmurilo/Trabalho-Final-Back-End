package com.mrlmurilo.uninter.domain.profissional;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profissionais_saude")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfissionalSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Column(nullable = false, unique = true)
    private String registroProfissional; // CRM, COREN etc

    private String telefone;

    private String email;
}
