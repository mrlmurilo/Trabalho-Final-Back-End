package com.mrlmurilo.uninter.domain.agenda;

import com.mrlmurilo.uninter.domain.profissional.ProfissionalSaude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "agendas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private ProfissionalSaude profissional;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAgenda status;

}

