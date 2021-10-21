package net.weg.gestor.api.model.projeto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ProjetoAlocarDTO {

    private Long id;

    private String nome;

    private String nomeResponsavel;

    private LocalDate dataCadastro;

    private boolean isAllocated;

}
