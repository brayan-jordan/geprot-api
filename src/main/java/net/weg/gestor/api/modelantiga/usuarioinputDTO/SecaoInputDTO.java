package net.weg.gestor.api.modelantiga.usuarioinputDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SecaoInputDTO {

    @NotNull
    private Long id;

}
