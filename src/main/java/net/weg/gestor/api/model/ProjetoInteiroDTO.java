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
    private LocalDateTime datainicio;
    private LocalDateTime datafinalizacao;
    private int horasprevistas;
    private int horastrabalhadas;
    private double valor;
    private double valorutilizado;
    private double valorrestante;
    private StatusProjeto status;
    private UsuarioDTO usuario;
    private List<CCPagantesDTO> ccpagantes;

}
