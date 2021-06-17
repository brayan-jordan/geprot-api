package net.weg.gestor.model.gestorinput;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SecaoInput {

    @NotNull
    private long idsecao;

}
