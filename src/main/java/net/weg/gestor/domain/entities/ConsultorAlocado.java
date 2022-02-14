package net.weg.gestor.domain.entities;

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
public class ConsultorAlocado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "projetos_id")
    private Projeto projeto;

    @NotNull
    @JoinColumn(name = "consultores_id")
    @ManyToOne
    private Consultor consultor;

    @NotNull
    private int limiteHoras;

    private int horasApontadas;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    public ConsultorAlocado(Projeto projeto, Consultor consultor, int limiteHoras, Skill skill) {
        this.projeto = projeto;
        this.consultor = consultor;
        this.limiteHoras = limiteHoras;
        this.skill = skill;
    }
}
