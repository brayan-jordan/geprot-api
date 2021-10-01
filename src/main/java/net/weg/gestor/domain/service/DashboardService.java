package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.BaseDashboardConcluidosDTO;
import net.weg.gestor.api.model.BasePorMesDashboardDTO;
import net.weg.gestor.api.model.BasePorPeriodoDashboardDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DashboardService {

    private ProjetoService projetoService;

//    public List<BasePorMesDashboardDTO> buscar7days(long secaoId) {
//        List<BasePorMesDashboardDTO> lista = new ArrayList<>();
//        for (int i = 1; i < 8; ++i) {
//            BasePorMesDashboardDTO base = new BasePorMesDashboardDTO();
//            base.setMesAno(LocalDate.now().minusDays(i).format(DateTimeFormatter.ofPattern("dd/MM")));
//            base.setQuantidade(projetoService.countProjetosConcluidos(secaoId, LocalDate.now().minusDays(i)));
//            lista.add(base);
//        }
//        return  lista;
//    }

//    public List<BasePorMesDashboardDTO> buscarUltimoMes(long secaoId) {
//        List<BasePorMesDashboardDTO> lista = new ArrayList<>();
//        for (long i = 0; i < 4; ++i) {
//            BasePorMesDashboardDTO base = new BasePorMesDashboardDTO();
//            LocalDate dataFinalPeriodo, dataInicioPeriodo;
//            dataFinalPeriodo = LocalDate.now().minusDays(1);dataFinalPeriodo = LocalDate.now().minusDays((i * 7 + 1));
//            dataInicioPeriodo = dataFinalPeriodo.minusDays(6);
//            String teste1 = dataInicioPeriodo.format(DateTimeFormatter.ofPattern("dd/MM"));
//            String teste2 = dataFinalPeriodo.format(DateTimeFormatter.ofPattern("dd/MM"));
//            base.setMesAno(teste1 + " - " + teste2);
//            base.setQuantidade(projetoService.countProjetosConcluidosPorPeriodo(secaoId, dataInicioPeriodo, dataFinalPeriodo));
//            lista.add(base);
//        }
//        return  lista;
//    }

//    public List<BasePorMesDashboardDTO> buscarUltimos6meses(long secaoId) {
//        List<BasePorMesDashboardDTO> lista = new ArrayList<>();
//        for (long i = 0; i < 6; ++i) {
//            LocalDate dataParaUsar = LocalDate.now();
//            if (i != 0) {
//                dataParaUsar = dataParaUsar.minusMonths(i);
//            }
//            BasePorMesDashboardDTO base = new BasePorMesDashboardDTO();
//            base.setMesAno(dataParaUsar.getMonth()+" "+dataParaUsar.getYear());
//            base.setQuantidade(projetoService.countProjetosConcluidosPorMes(secaoId, dataParaUsar.getMonth(), dataParaUsar.getYear()));
//            lista.add(base);
//        }
//        return  lista;
//    }

//    public List<BasePorMesDashboardDTO> buscarUltimoAno(long secaoId) {
//        List<BasePorMesDashboardDTO> lista = new ArrayList<>();
//        for (long i = 0; i < 12; ++i) {
//            LocalDate dataParaUsar = LocalDate.now();
//            if (i != 0) {
//                dataParaUsar = dataParaUsar.minusMonths(i);
//            }
//            BasePorMesDashboardDTO base = new BasePorMesDashboardDTO();
//            base.setMesAno(dataParaUsar.format(DateTimeFormatter.ofPattern("MM/yy")));
//            base.setQuantidade(projetoService.countProjetosConcluidosPorMes(secaoId, dataParaUsar.getMonth(), dataParaUsar.getYear()));
//            lista.add(base);
//        }
//        return  lista;
//    }

}
