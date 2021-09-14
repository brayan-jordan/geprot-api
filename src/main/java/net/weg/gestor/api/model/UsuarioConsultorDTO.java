package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioConsultorDTO {

    private Long id;
    private String nome;
    private int demandas;
    private FornecedorDTO fornecedor;

}
