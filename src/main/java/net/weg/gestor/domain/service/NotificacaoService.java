package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.NotificacaoDTO;
import net.weg.gestor.api.model.notificacaoinputDTO.NotificacaoInputDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificacaoService {

    private UsuarioRepository usuarioRepository;

//    public String adicionarNotificacao(NotificacaoInputDTO notificacao) {
//        if (!usuarioRepository.existsById(notificacao.getUsuarios_id())) {
//            throw new NegocioException("Nao existe um usuario com esse ID, para ser notificado");
//        }
//
//
//    }

}
