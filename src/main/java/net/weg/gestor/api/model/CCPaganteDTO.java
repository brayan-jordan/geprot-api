package net.weg.gestor.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CCPaganteDTO {

    private Long id;

    private String nome;

    private double valorPagante;

}
