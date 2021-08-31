package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.model.StatusProjeto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NotNull
public class ProjetoInteiroDTO {


    private Long id;

    private String nome;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFinalizacao;

    private LocalDateTime dataCadastro;

    private int horasPrevistas;

    private int horasTrabalhadas;

    private double valor;

    private double valorUtilizado;

    private StatusProjeto status;

    private List<CCPagantesDTO> ccpagantes;

}
