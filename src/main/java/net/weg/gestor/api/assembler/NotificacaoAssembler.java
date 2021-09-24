package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.InfoNotificacoesDTO;
import net.weg.gestor.api.model.NotificacaoDTO;
import net.weg.gestor.api.model.notificacaoinputDTO.NotificacaoInputDTO;
import net.weg.gestor.domain.model.Notificacao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class NotificacaoAssembler {

    private ModelMapper modelMapper;

    public NotificacaoDTO toModel(Notificacao notificacao) {
        return modelMapper.map(notificacao, NotificacaoDTO.class);
    }

    public Notificacao toEntity(NotificacaoInputDTO notificacao) {
        return modelMapper.map(notificacao, Notificacao.class);
    }

    public List<NotificacaoDTO> toCollectionModel(List<Notificacao> notificacoes) {
        return notificacoes.stream().map(this::toModel).collect(Collectors.toList());
    }

}
