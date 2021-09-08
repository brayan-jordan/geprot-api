package net.weg.gestor.api.model.apontarinputDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApontamentoDeHoraInputDTO {

    private Long projeto_id;

    private Long usuario_id;

    private int quantidade_horas;

    private String descricao_trabalho;

}
