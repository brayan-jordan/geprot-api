package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ColunaHoraApontadaDTO {

    private LocalDate data;

    private String descricao_trabalho;

    private int quantidade_horas;

    private String status;

}
