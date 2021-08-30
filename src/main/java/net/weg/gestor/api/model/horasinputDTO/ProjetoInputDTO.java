package net.weg.gestor.api.model.horasinputDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProjetoInputDTO {

    @NotNull
    private Long id;

}
