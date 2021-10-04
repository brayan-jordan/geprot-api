package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.entities.TypeNotificacao;

import java.time.LocalDate;

@Getter
@Setter
public class NotificacaoDTO {

    private Long id;

    private TypeNotificacao tipo;

    private String descricao;

    private LocalDate data;

    private boolean statusLeitura;

}
