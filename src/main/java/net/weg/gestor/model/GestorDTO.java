package net.weg.gestor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.model.Secao;

@Getter
@Setter
@AllArgsConstructor
public class GestorDTO {

    long idgestor;

    String senha;

    private Secao secao;

    String nomegestor;

    String email;
}
