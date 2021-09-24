package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class InfoNotificacoesDTO {

    private int quantidade;
    private List<NotificacaoDTO> notificacoes;

}
