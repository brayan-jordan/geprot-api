package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.ConsultorAlocadoDTO;
import net.weg.gestor.domain.service.ConsultoresAlocadosService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultores")
@AllArgsConstructor
public class ConsultoresController {

    private ConsultoresAlocadosService consultoresAlocadosService;

    @PostMapping("/alocar/{projetoId}/{consultorId}")
    public ConsultorAlocadoDTO alocarConsultor(@PathVariable Long projetoId, @PathVariable Long consultorId) {
        return consultoresAlocadosService.alocarConsultor(projetoId, consultorId);
    }

}
