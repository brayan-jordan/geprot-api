package net.weg.gestor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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

    private Long projetos_id;

    @NotNull
    @Size(min = 1, max = 24)
    private int quantidade_horas;

    @NotNull
    private LocalDate data;

    private Long consultores_id;

    @NotBlank
    private String descricao_trabalho;

    private String status;

}
