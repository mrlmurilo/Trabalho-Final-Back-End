package com.mrlmurilo.uninter.domain.consulta;

import com.mrlmurilo.uninter.domain.agenda.Agenda;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoConsulta tipo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusConsulta status;
}
