package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.ConsultorAlocadoDTO;
import net.weg.gestor.domain.service.ConsultoresAlocadosService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ConsultoresController {

    private ConsultoresAlocadosService consultoresAlocadosService;

    public ConsultorAlocadoDTO alocarConsultor(Long projetoId, Long consultorId) {
        return consultoresAlocadosService.alocarConsultor(projetoId, consultorId);
    }

}
