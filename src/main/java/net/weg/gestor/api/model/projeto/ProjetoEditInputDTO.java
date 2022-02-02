package net.weg.gestor.api.model.projeto;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.api.model.ConsultorEditarProjetoDTO;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProjetoEditInputDTO {

    private String nome;

    private String descricao;

    private LocalDate dataEncerramento;

    private int horasAprovadas;

    private double verbasAprovadas;

    private List<ConsultorEditarProjetoDTO> consultores;

}
