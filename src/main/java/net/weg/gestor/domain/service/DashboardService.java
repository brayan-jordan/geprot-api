package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.BaseDashboardConcluidosDTO;
import net.weg.gestor.api.model.BasePorMesDashboardDTO;
import net.weg.gestor.api.model.BasePorPeriodoDashboardDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DashboardService {

    private ProjetoService projetoService;

    public List<BaseDashboardConcluidosDTO> buscar7days(long secaoId) {
        List<BaseDashboardConcluidosDTO> lista = new ArrayList<>();
        for (int i = 1; i < 8; ++i) {
            BaseDashboardConcluidosDTO base = new BaseDashboardConcluidosDTO();
            base.setData(LocalDate.now().minusDays(i));
            base.setQuantidade(projetoService.countProjetosConcluidos(secaoId, LocalDate.now().minusDays(i)));
            lista.add(base);
        }
        return  lista;
    }

    public List<BasePorPeriodoDashboardDTO> buscarUltimoMes(long secaoId) {
        List<BasePorPeriodoDashboardDTO> lista = new ArrayList<>();
        for (long i = 0; i < 4; ++i) {
            BasePorPeriodoDashboardDTO base = new BasePorPeriodoDashboardDTO();
            // data
            LocalDate dataFinalPeriodo, dataInicioPeriodo;
            // verificação, caso o index for igual a 0 é o primeiro periodo, o que indica que terminou a apenas um dia
            // nao sendo necessário nenhuma multiplicação
            if (i == 0) {
                dataFinalPeriodo = LocalDate.now().minusDays(1);
            } else {
                dataFinalPeriodo = LocalDate.now().minusDays((i * 7 + 1));
            }
            dataInicioPeriodo = dataFinalPeriodo.minusDays(6);
            base.setFinalPeriodo(dataFinalPeriodo);
            base.setInicioPeriodo(dataInicioPeriodo);
            base.setQuantidade(projetoService.countProjetosConcluidosPorPeriodo(secaoId, dataInicioPeriodo, dataFinalPeriodo));
            lista.add(base);
        }
        return  lista;
    }

    public List<BasePorMesDashboardDTO> buscarUltimos6meses(long secaoId) {
        List<BasePorMesDashboardDTO> lista = new ArrayList<>();
        for (long i = 0; i < 6; ++i) {
            LocalDate dataParaUsar = LocalDate.now();
            if (i != 0) {
                dataParaUsar = dataParaUsar.minusMonths(i);
            }
            BasePorMesDashboardDTO base = new BasePorMesDashboardDTO();
            base.setMesAno(dataParaUsar.getMonth()+" "+dataParaUsar.getYear());
            base.setQuantidade(projetoService.countProjetosConcluidosPorMes(secaoId, dataParaUsar.getMonth(), dataParaUsar.getYear()));
            lista.add(base);
        }
        return  lista;
    }

    public List<BasePorMesDashboardDTO> buscarUltimoAno(long secaoId) {
        List<BasePorMesDashboardDTO> lista = new ArrayList<>();
        for (long i = 0; i < 12; ++i) {
            LocalDate dataParaUsar = LocalDate.now();
            if (i != 0) {
                dataParaUsar = dataParaUsar.minusMonths(i);
            }
            BasePorMesDashboardDTO base = new BasePorMesDashboardDTO();
            base.setMesAno(dataParaUsar.getMonth()+" "+dataParaUsar.getYear());
            base.setQuantidade(projetoService.countProjetosConcluidosPorMes(secaoId, dataParaUsar.getMonth(), dataParaUsar.getYear()));
            lista.add(base);
        }
        return  lista;
    }

}
