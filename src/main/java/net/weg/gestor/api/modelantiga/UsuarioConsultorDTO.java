package net.weg.gestor.api.modelantiga;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.entities.StatusUsuario;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioConsultorDTO {

    private Long id;
    private String nome;
    private int demandas;
    private String email;
    private double precoHora;
    private FornecedorDTO fornecedor;
    private LocalDate dataCadastro;
    private StatusUsuario status;
    private String dataFormatada;

}
