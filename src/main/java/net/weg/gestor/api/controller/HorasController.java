package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.api.model.consultor.ConsultorAlocadoDTO;
import net.weg.gestor.api.model.consultorhoras.ConsultorComSuasHorasApontadas;
import net.weg.gestor.domain.service.HorasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horas")
@AllArgsConstructor
@Getter
@Setter
public class    HorasController {

    private HorasService horasService;

    @GetMapping("/{projetoId}")
    public List<ConsultorAlocadoDTO> buscarConsultoresAlocadosProjeto(@PathVariable Long projetoId) {
        return horasService.buscarConsultoresAlocadosProjeto(projetoId);
    }

    @GetMapping("/{projetoId}/{consultorId}")
    public ConsultorComSuasHorasApontadas buscarHorasConsultor(@PathVariable Long projetoId, @PathVariable Long consultorId) {
        return horasService.buscarHorasConsultor(projetoId, consultorId);
    }

    @PutMapping("/aprovar/{projetoId}/{consultorId}")
    public String aprovarHoras(@PathVariable Long projetoId, @PathVariable Long consultorId) {
        return horasService.aprovarHoras(projetoId, consultorId);
    }

}
