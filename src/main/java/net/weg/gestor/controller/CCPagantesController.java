package net.weg.gestor.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.service.CCPagantesService;
import net.weg.gestor.model.CCPagantesModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    

}
