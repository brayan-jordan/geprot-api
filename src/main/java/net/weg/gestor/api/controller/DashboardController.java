package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.weg.gestor.api.model.BaseDashboardConcluidosDTO;
import net.weg.gestor.api.model.BasePorMesDashboardDTO;
import net.weg.gestor.api.model.BasePorPeriodoDashboardDTO;
import net.weg.gestor.domain.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@AllArgsConstructor
@Getter
@Setter
public class DashboardController {

    private DashboardService dashboardService;

    @GetMapping("/concluidos/7dias/{secaoId}")
    public List<BaseDashboardConcluidosDTO> buscar7days(@PathVariable long secaoId) {
        return dashboardService.buscar7days(secaoId);
    }

    @GetMapping("/concluidos/ultimoMes/{secaoId}")
    public List<BasePorPeriodoDashboardDTO> buscarUltimoMes(@PathVariable long secaoId) {
        return dashboardService.buscarUltimoMes(secaoId);
    }

    @GetMapping("/concluidos/ultimos6Meses/{secaoId}")
    public List<BasePorMesDashboardDTO> buscarUltimos6meses(@PathVariable long secaoId) {
        return dashboardService.buscarUltimos6meses(secaoId);
    }

    @GetMapping("/concluidos/ultimoano/{secaoId}")
    public List<BasePorMesDashboardDTO> buscarUltimoAno(@PathVariable long secaoId) {
        return dashboardService.buscarUltimoAno(secaoId);
    }

}
