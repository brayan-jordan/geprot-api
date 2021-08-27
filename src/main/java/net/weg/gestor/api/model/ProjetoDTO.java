package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.model.StatusProjeto;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProjetoDTO {

    private Long id;
    private String nome;
    private LocalDateTime datainicio;
    private LocalDateTime datafinalizacao;
    private LocalDateTime datacadastro;
    private int horasprevistas;
    private int horastrabalhadas;
    private double valor;
    private double valorutilizado;
    private double valorrestante;
    private StatusProjeto status;
    private UsuarioDTO usuario;


}
