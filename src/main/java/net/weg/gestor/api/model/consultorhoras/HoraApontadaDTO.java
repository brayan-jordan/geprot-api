package net.weg.gestor.api.model.consultorhoras;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.entities.StatusApontamento;

import java.time.LocalDate;

@Getter
@Setter
public class HoraApontadaDTO {

    private int quantidadeHoras;

    private LocalDate data;

    private String descricaoTrabalho;

    private StatusApontamento status;

}
