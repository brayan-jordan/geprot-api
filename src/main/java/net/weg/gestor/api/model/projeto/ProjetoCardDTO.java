package net.weg.gestor.api.model.projeto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int horasPrevistas;
    private int horasTrabalhadas;
    private double valor;

    @JsonIgnore
    private double valorUtilizado;

    private double valorRestante;
    private String nomeReponsavel;
    private StatusProjeto status;
    private double barraProgresso;

}
