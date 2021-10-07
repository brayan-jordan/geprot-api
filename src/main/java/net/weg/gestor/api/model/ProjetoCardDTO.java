package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.entities.StatusProjeto;

import java.time.LocalDate;

@Getter
@Setter
public class ProjetoCardDTO {

    private Long id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFinalizacao;
    private LocalDate dataCadastro;
    private int horasPrevistas;
    private int horasTrabalhadas;
    private double valor;
    private double valorUtilizado;
    private double valorRestante;
    private StatusProjeto status;

}
