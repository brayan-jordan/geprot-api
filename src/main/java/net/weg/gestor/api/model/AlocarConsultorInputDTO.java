package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlocarConsultorInputDTO {

    private Long projetos_id;

    private Long usuarios_id;

    private int limiteHoras;

}
