package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.weg.gestor.api.model.InfoNotificacoesDTO;
import net.weg.gestor.domain.service.NotificacaoService;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    private NotificacaoService notificacaoService;

    @PostMapping("/fechamentomes/{usuarioId}")
    public String addNotificacaoFechamento(@PathVariable Long usuarioId) {
        return notificacaoService.addNotificacaoFechamento(usuarioId);
    }

    @GetMapping("buscar/{usuarioId}")
    public InfoNotificacoesDTO buscarNotificacoes(@PathVariable Long usuarioId) {
        return notificacaoService.buscarTodasNotificacoes(usuarioId);
    }

    @PutMapping("/marcarlida/{notificacaoId}")
    public String marcarLida(@PathVariable Long notificacaoId) {
        return notificacaoService.marcarLida(notificacaoId);
    }

}
