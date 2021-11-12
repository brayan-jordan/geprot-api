package net.weg.gestor.api.model.cadastrarprojetoinput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjetoConsultoresInputDTO {

    private Long consultorId;

    private int quantidadeHoras;

    private int numeroDaSkill;

}