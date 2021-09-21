package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.BaseDashboardConcluidosDTO;
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

}
