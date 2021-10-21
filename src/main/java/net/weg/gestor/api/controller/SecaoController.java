package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.SecaoDTO;
import net.weg.gestor.domain.service.SecaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secao")
@AllArgsConstructor
public class SecaoController {
    /*  Nessa classe é controlada as rotas das seções para a listagem de dados  */

    private SecaoService secaoService;

    @GetMapping("/listar")
    public List<SecaoDTO> listarTodas(){
        return secaoService.listarTodas();
    }

    @GetMapping("/buscar/{secaoId}")
    public SecaoDTO buscarSecao(@PathVariable long secaoId){
        return secaoService.buscarSecao(secaoId);
    }

}
