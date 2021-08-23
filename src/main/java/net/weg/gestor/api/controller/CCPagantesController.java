package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.service.CCPagantesService;
import net.weg.gestor.api.model.CCPagantesModel;
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

    @GetMapping
    public List<CCPagantesModel> listartodos() {
        return ccPagantesService.listartodos();
    }

    @GetMapping("/listarporprojeto/{projetoid}")
    public List<CCPagantesModel> listarPorProjeto(@PathVariable Long projetoid) {
        return ccPagantesService.listarporprojeto(projetoid);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CCPagantesModel cadastrarNovoccpagante(@Valid @RequestBody CCPagantesInputDTO ccPagantesInputDTO) {
        return ccPagantesService.cadastrar(ccPagantesInputDTO);

    }

}
