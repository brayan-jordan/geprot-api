package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.NotificacaoAssembler;
import net.weg.gestor.domain.entities.Usuario;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.entities.Notificacao;
import net.weg.gestor.domain.repository.NotificacaoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class NotificacaoService {

    private UsuarioRepository usuarioRepository;
    private NotificacaoRepository notificacaoRepository;
    private NotificacaoAssembler notificacaoAssembler;

//    public String addNotificacaoFechamento(Long usuarioId) {
//        if (usuarioRepository.findById(usuarioId).isEmpty()) {
//            throw new NegocioException("Nao existe um usuario com esse ID para notificar");
//        }
//
//        notificacaoRepository.save(preencherDados(usuarioId));
//        return "Notificado";
//    }

//    public InfoNotificacoesDTO buscarTodasNotificacoes(Long usuarioId) {
//        if (usuarioRepository.findById(usuarioId).isEmpty()) {
//            throw new NegocioException("Nao existe um usuario com esse ID para notificar");
//        }
//
//        InfoNotificacoesDTO infoNotificacoesDTO = new InfoNotificacoesDTO();
//        infoNotificacoesDTO.setNotificacoes(notificacaoAssembler.toCollectionModel(
//                notificacaoRepository.buscarTodasPorUsuario(usuarioRepository.findByIdUsuario(usuarioId))));
//        infoNotificacoesDTO.setQuantidade(buscarNaoLidas(usuarioId));
//        return infoNotificacoesDTO;
//    }

    public String marcarLida(Long notificacaoId) {
        Notificacao notificacao = notificacaoRepository.findById(notificacaoId).orElseThrow(() -> new NegocioException("Erro"));
        notificacao.setStatusLeitura(true);
        notificacaoRepository.save(notificacao);
        return "Deu boa";

    }

//    public Notificacao preencherDados(long usuarioId) {
//        Notificacao notificacao = new Notificacao();
//        notificacao.setData(LocalDate.now());
//        notificacao.setDescricao("ATENÇÃO! Inicio de um novo mês, está na hora de aprovar as horas pendentes");
//        notificacao.setStatusLeitura(false);
//        notificacao.setUsuario(usuarioRepository.findByIdUsuario(usuarioId));
//        notificacao.setTipo(TypeNotificacao.FECHAMENTO_MES);
//        return notificacao;
//    }

//    public int buscarNaoLidas(Long usuarioId) {
//        return notificacaoRepository.buscarQuantidadeNaoLida(usuarioRepository.findByIdUsuario(usuarioId)).size();
//    }

    public String adicionarNovaNotificacao(String descricao, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new NegocioException("Usuario nao encontrado para esse ID")
        );

        notificacaoRepository.save(new Notificacao(descricao, usuario, LocalDate.now(), false));

        return "Notificado";
    }

}
