package net.weg.gestor.api.model.cadastrarprojetoinput;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.api.model.input.ConsultorInputDTO;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProjetoInputDTO {

    private String nome;

    private String nomeResponsavel;

    private String alfanumericoAta;

    private String nomeSolicitante;

    private String descricao;

    private LocalDate dataInicio;

    private LocalDate dataFinalizacao;

    private List<ProjetoConsultoresInputDTO> consultores;

    private List<ProjetoCCPagantesInputDTO> ccpagantes;

    private double verba;

}
