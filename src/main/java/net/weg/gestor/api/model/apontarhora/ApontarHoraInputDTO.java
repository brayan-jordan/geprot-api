package net.weg.gestor.api.model.apontarhora;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class ApontarHoraInputDTO {

    private Long consultorId;

    private Long projetoId;

    private int quantidadeHoras;

    private String descricaoApontamento;

}
