package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.service.SecaoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secao")
@AllArgsConstructor
public class SecaoController {

    private SecaoService secaoService;

//    @PutMapping("/editar/{usuarioId}")
//    public UsuarioDTO alterarSecao(@PathVariable long usuarioId, @RequestBody SecaoInputDTO secaoInputDTO){
//        return secaoService.editarSecao(usuarioId, secaoInputDTO);
//    }
//
//    @GetMapping("/listar/{secaoId}")
//    public DashboardSecaoDTO listarDashboard(@PathVariable Long secaoId){
//        return secaoService.listarDashboard(secaoId);
//    }
}
