package net.weg.gestor.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class GestorModel {

    private long idgestor;
    private String nomegestor;
    private String senha;
    private String email;
    private SecaoModel secao;

}
