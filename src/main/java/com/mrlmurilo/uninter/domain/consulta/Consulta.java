package com.mrlmurilo.uninter.domain.consulta;

import com.mrlmurilo.uninter.domain.paciente.Paciente;
import com.mrlmurilo.uninter.domain.profissional.ProfissionalSaude;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Paciente paciente;

    @ManyToOne(optional = false)
    private ProfissionalSaude profissional;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoConsulta tipo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusConsulta status;
}
