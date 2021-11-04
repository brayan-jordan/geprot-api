package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.NotificacaoAssembler;
import net.weg.gestor.api.model.NotificacaoDTO;
import net.weg.gestor.domain.entities.Usuario;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.entities.Notificacao;
import net.weg.gestor.domain.repository.NotificacaoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificacaoService {

    private UsuarioRepository usuarioRepository;
    private NotificacaoRepository notificacaoRepository;
    private NotificacaoAssembler notificacaoAssembler;

    public String marcarLida(Long notificacaoId) {
        Notificacao notificacao = notificacaoRepository.findById(notificacaoId).orElseThrow(() -> new NegocioException("Notificacao nao encontrada"));
        notificacao.setStatusLeitura(true);
        notificacaoRepository.save(notificacao);
        return "Deu boa";
    }

    public String adicionarNovaNotificacao(String descricao, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new NegocioException("Usuario nao encontrado para esse ID")
        );

        notificacaoRepository.save(new Notificacao(descricao, usuario, LocalDate.now(), false));
        return "Notificado";
    }

    public List<NotificacaoDTO> buscarNotificacoesUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new NegocioException("Usuario nao encontrado para esse ID")
        );

        return notificacaoAssembler.toCollectionModel(notificacaoRepository.buscarNotificacoesUsuario(usuario));
    }

}
