package net.weg.gestor.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "horas_apontadas")
public class HoraApontada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "projetos_id")
    @ManyToOne
    private Projeto projeto;

    @NotNull
    @Size(min = 1, max = 24)
    private int quantidadeHoras;

    @NotNull
    private LocalDate data;

    @JoinColumn(name = "consultores_id")
    @ManyToOne
    private Consultor consultor;

    @NotBlank
    private String descricaoTrabalho;

    private StatusApontamento status;

}
