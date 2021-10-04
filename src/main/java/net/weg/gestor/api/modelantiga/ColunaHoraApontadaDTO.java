package net.weg.gestor.api.modelantiga;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ColunaHoraApontadaDTO {

    private LocalDate data;

    private String descricao_trabalho;

    private int quantidade_horas;

    private String status;

}
