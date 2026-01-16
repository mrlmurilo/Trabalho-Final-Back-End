package com.mrlmurilo.uninter.domain.prontuario;

import com.mrlmurilo.uninter.domain.consulta.Consulta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "prontuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "consulta_id", unique = true)
    private Consulta consulta;

    @Column(nullable = false, length = 2000)
    private String descricao;

    @Column(length = 2000)
    private String prescricao;

    @Column(nullable = false)
    private LocalDateTime dataRegistro;
}
