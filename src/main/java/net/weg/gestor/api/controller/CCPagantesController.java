package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.CCPaganteDTO;
import net.weg.gestor.domain.service.CCPagantesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/ccpagantes")
public class CCPagantesController {

    private CCPagantesService ccPagantesService;

    @GetMapping("/buscar/{projetoId}")
    public List<CCPaganteDTO> buscarCCpagantesProjeto(@PathVariable Long projetoId) {
        return ccPagantesService.buscarCCpagantesProjeto(projetoId);
    }

}
