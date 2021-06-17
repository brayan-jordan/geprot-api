package net.weg.gestor.model;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.model.StatusProjeto;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProjetoModel {

    private Long idprojeto;
    private String nomeprojeto;
    private LocalDateTime datainicio;
    private LocalDateTime datafinalizacao;
    private int horasprevistas;
    private int horastrabalhadas;
    private double valorprojeto;
    private double valorutilizado;
    private double valorrestante;
    private GestorModel gestor;
    private StatusProjeto statusprojeto;

}
