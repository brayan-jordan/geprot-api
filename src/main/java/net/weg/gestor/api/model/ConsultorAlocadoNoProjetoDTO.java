package net.weg.gestor.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.weg.gestor.api.model.input.SkillDTO;
import net.weg.gestor.domain.entities.Skill;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultorAlocadoNoProjetoDTO {

    private Long id;

    private String nome;

    private Skill skill;

    private int horasAlocadas;

}
