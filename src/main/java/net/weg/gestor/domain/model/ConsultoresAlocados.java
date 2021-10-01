package net.weg.gestor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "consultores_alocados")
public class ConsultoresAlocados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JoinColumn(name = "projetos_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Projeto projeto;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consultores_id")
    private Consultor consultor;

    @NotNull
    private int limiteHoras;

    private int horasApontadas;

}
