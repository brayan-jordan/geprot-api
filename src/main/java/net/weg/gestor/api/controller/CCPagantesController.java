package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.service.CCPagantesService;
import net.weg.gestor.api.model.CCPagantesDTO;
import net.weg.gestor.api.model.centrodecustoinputDTO.CCPagantesInputDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/ccpagantes")
public class CCPagantesController {

    private CCPagantesService ccPagantesService;

    @GetMapping("/listar")
    public List<CCPagantesDTO> listar() {
        return ccPagantesService.listartodos();
    }

//    @GetMapping("/listarporprojeto/{projetoid}")
//    public List<CCPagantesDTO> listarPorProjeto(@PathVariable Long projetoid) {
//        return ccPagantesService.listarporprojeto(projetoid);
//    }

//    @PostMapping("/cadastrar")
//    @ResponseStatus(HttpStatus.CREATED)
//    public List<CCPagantesDTO> cadastrar(@Valid @RequestBody List<CCPagantesInputDTO> ccPagantesInputDTO) {
//        return ccPagantesService.cadastrar(ccPagantesInputDTO);
//
//    }

}
