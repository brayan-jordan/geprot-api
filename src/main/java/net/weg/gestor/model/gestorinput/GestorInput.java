package net.weg.gestor.model.gestorinput;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GestorInput {

    private SecaoInput secao;
    private String nomegestor;
    private long idgestor;
    private String senha;
    private String email;

}
