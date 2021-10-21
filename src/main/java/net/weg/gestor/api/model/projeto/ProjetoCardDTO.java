package net.weg.gestor.api.model.projeto;

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
    private String nomeReponsavel;
    private StatusProjeto status;
    private double barraProgresso;

}