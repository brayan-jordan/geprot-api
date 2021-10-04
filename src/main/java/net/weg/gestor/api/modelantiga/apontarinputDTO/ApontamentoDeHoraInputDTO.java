package net.weg.gestor.api.modelantiga.apontarinputDTO;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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

    @Length(min = 20)
    private String descricao_trabalho;

}