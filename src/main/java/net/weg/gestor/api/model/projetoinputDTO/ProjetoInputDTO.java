package net.weg.gestor.api.model.projetoinputDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjetoInputDTO {

    private UsuarioToProjetoInputDTO usuario;
    private String nome;
    private int horasprevistas;
    private double valor;

}
