package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.NotificacaoDTO;
import net.weg.gestor.domain.service.NotificacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    private NotificacaoService notificacaoService;

/*
    @PostMapping("/fechamentomes/{usuarioId}")
    public String addNotificacaoFechamento(@PathVariable Long usuarioId) {
        return notificacaoService.addNotificacaoFechamento(usuarioId);
    }

    @GetMapping("buscar/{usuarioId}")
    public InfoNotificacoesDTO buscarNotificacoes(@PathVariable Long usuarioId) {
        return notificacaoService.buscarTodasNotificacoes(usuarioId);
    }
*/

    @GetMapping("/{usuarioId}")
    public List<NotificacaoDTO> buscarNotificacoesUsuario(@PathVariable Long usuarioId) {
        return notificacaoService.buscarNotificacoesUsuario(usuarioId);
    }

    @GetMapping("buscar/{notificacaoId}")
    public NotificacaoDTO buscarNotificacoesId(@PathVariable Long notificacaoId) {
        return notificacaoService.buscarnotificacaoId(notificacaoId);
    }

    @PutMapping("/marcarlida/{notificacaoId}")
    public String marcarLida(@PathVariable Long notificacaoId) {
        return notificacaoService.marcarLida(notificacaoId);
    }

}
