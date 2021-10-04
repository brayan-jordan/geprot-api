package net.weg.gestor.api.modelantiga;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InfoNotificacoesDTO {

    private int quantidade;
    private List<NotificacaoDTO> notificacoes;

}
