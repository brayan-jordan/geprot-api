package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.AlocarConsultorInputDTO;
import net.weg.gestor.api.model.ConsultorAlocadoDTO;
import net.weg.gestor.api.model.ConsultorDTO;
import net.weg.gestor.domain.service.ConsultoresAlocadosService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultores")
@AllArgsConstructor
public class ConsultoresController {

    private ConsultoresAlocadosService consultoresAlocadosService;

    @PostMapping("/alocar")
    public ConsultorDTO alocarConsultor(@RequestBody AlocarConsultorInputDTO alocarConsultorInputDTO) {
        return consultoresAlocadosService.alocarConsultor(alocarConsultorInputDTO);
    }

}
