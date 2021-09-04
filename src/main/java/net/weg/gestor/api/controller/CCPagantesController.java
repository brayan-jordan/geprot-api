package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.service.CCPagantesService;
import net.weg.gestor.api.model.SecoesDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/ccpagantes")
public class CCPagantesController {

    private CCPagantesService ccPagantesService;

    @GetMapping("/listar")
    public List<SecoesDTO> listar() {
        return ccPagantesService.listartodos();
    }

    @GetMapping("/listarporprojeto/{projetoid}")
    public List<SecoesDTO> listarPorProjeto(@PathVariable Long projetoid) {
        return ccPagantesService.listarporprojeto(projetoid);
    }

//    @PostMapping("/cadastrar")
//    @ResponseStatus(HttpStatus.CREATED)
//    public List<CCPagantesDTO> cadastrar(@Valid @RequestBody List<CCPagantesInputDTO> ccPagantesInputDTO) {
//        return ccPagantesService.cadastrar(ccPagantesInputDTO);
//
//    }

}
