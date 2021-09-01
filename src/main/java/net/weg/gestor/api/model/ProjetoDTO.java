package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.model.StatusProjeto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProjetoDTO {

    private Long id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFinalizacao;
    private LocalDate dataCadastro;
    private int horasPrevistas;
    private int horasTrabalhadas;
    private int horasRestantes;
    private double valor;
    private double valorUtilizado;
    private double valorRestante;
    private StatusProjeto status;
    private List<CCPagantesDTO> centroDeCustos;
    private List<ConsultorDTO> usuarios;


}
