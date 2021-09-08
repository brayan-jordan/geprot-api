package net.weg.gestor.api.model.apontarinputDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ApontamentoDeHoraInputDTO {

    @NotNull
    private Long projetos_id;

    @NotNull
    private Long usuarios_id;

    @NotNull
    private int quantidade_horas;

    @NotNull
    private String descricao_trabalho;

}
