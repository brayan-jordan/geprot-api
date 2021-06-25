package net.weg.gestor.api.model.centrodecustoinput;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CCPagante_CCInput {

    @NotNull
    private Long codigo;

}
