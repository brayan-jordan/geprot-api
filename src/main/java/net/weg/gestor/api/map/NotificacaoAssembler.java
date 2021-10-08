package net.weg.gestor.api.map;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificacaoAssembler {

    private ModelMapper modelMapper;

//    public NotificacaoDTO toModel(Notificacao notificacao) {
//        return modelMapper.map(notificacao, NotificacaoDTO.class);
//    }
//
//    public Notificacao toEntity(NotificacaoInputDTO notificacao) {
//        return modelMapper.map(notificacao, Notificacao.class);
//    }
//
//    public List<NotificacaoDTO> toCollectionModel(List<Notificacao> notificacoes) {
//        return notificacoes.stream().map(this::toModel).collect(Collectors.toList());
//    }

}
