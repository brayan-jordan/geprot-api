package net.weg.gestor.api.model.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AlocarConsultorInputDTO {

    private Long consultorId;

    private Long projetoId;

    private int quantidadeHoras;

    private int numeroSkill;

}
