package net.weg.gestor.api.model.projeto;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.entities.StatusProjeto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProjetoDetalhadoDTO {

    private Long id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFinalizacao;
    private LocalDate dataCadastro;
    private int horasPrevistas;
    private int horasTrabalhadas;
    private int horasRestantes;
    private String alfanumericoAta;
    private double valor;
    private double valorUtilizado;
    private double valorRestante;
    private StatusProjeto status;
    private String descricao;
    private String nomeSolicitante;
    private String nomeResponsavel;

}
