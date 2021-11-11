package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.dashboard.DashboardProjetosDTO;
import net.weg.gestor.api.model.dashboard.DashboardVerbaDTO;
import net.weg.gestor.domain.entities.Projeto;
import net.weg.gestor.domain.entities.Secao;
import net.weg.gestor.domain.entities.StatusProjeto;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.SecaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DashboardService {

    private ProjetoService projetoService;
    private SecaoRepository secaoRepository;
    private ProjetoRepository projetoRepository;
    private CCPagantesRepository ccPagantesRepository;

    public DashboardVerbaDTO listarVerbas(Long secaoId) {
        double verbaUtilizada;
        Secao secao = secaoRepository.findByIdAux(secaoId);
        DashboardVerbaDTO dashboardVerbaDTO = new DashboardVerbaDTO();
        List<Projeto> projetos = projetoService.buscarTodosProjetoSecao(secaoId);
        verbaUtilizada = projetos.stream().mapToDouble(projeto -> (projeto.getValor()) *
                (ccPagantesRepository.buscarTaxaCCpagantes(secao, projeto).getTaxa())
                / 100).sum();
        dashboardVerbaDTO.setVerbaUtilizada(verbaUtilizada);
        return dashboardVerbaDTO;
    }

    public DashboardProjetosDTO listarProjetos(long secaoId){
        DashboardProjetosDTO dashboardProjetosDTO = new DashboardProjetosDTO();
        dashboardProjetosDTO.setProjetosAtrasados(this.porcentualPorStatus(StatusProjeto.ATRASADO,secaoId));
        dashboardProjetosDTO.setRestoProjetosAtrasados(100 - dashboardProjetosDTO.getRestoProjetosAtrasados());
        dashboardProjetosDTO.setProjetosEmAndamento(this.porcentualPorStatus(StatusProjeto.EM_ANDAMENTO,secaoId));
        dashboardProjetosDTO.setRestoProjetosEmAndamento(100 - dashboardProjetosDTO.getProjetosEmAndamento());
        dashboardProjetosDTO.setProjetosNaoIniciados(this.porcentualPorStatus(StatusProjeto.NAO_INICIADO,secaoId));
        dashboardProjetosDTO.setRestoProjetosNaoIniciados(100 - dashboardProjetosDTO.getProjetosNaoIniciados());
        dashboardProjetosDTO.setProjetosConcluidos(this.porcentualPorStatus(StatusProjeto.CONCLUIDO,secaoId));
        dashboardProjetosDTO.setRestoProjetosConcluidos(100 - dashboardProjetosDTO.getProjetosConcluidos());

        return dashboardProjetosDTO;
    }

    public double porcentualPorStatus(StatusProjeto statusProjeto, long secaoId){
        List<Projeto> projetos = projetoService.buscarTodosProjetoSecao(secaoId);
        int qtdePorStatus = (int) projetos.stream().filter(projeto -> projeto.getStatus() == statusProjeto).count();
        return (qtdePorStatus * 100) / projetos.size();
    }

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
