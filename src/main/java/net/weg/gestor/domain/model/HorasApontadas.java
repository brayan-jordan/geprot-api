package net.weg.gestor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "horas_apontadas")
public class HorasApontadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "projetos_id")
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Projeto projeto;

    @NotNull
    @Size(min = 1, max = 24)
    private int quantidade_horas;

    @NotNull
    private LocalDateTime data_hora;

    @JoinColumn(name = "usuarios_id")
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    @NotBlank
    private String descricao_trabalho;

    private StatusApontamento status;

}
