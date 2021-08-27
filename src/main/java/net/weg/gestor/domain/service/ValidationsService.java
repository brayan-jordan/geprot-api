package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.centrodecustoinputDTO.CCPagantesInputDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.CentroDeCusto;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.CentroDeCustoRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ValidationsService {

    private ProjetoRepository projetoRepository;
    private CCPagantesRepository ccPagantesRepository;
    private CentroDeCustoRepository centroDeCustoRepository;

    public int calcularTaxa(List<CCPagantesInputDTO> lista) {
        int soma = 0;
        for (int i = 0; i < lista.size(); ++i) {
            soma += lista.get(i).getTaxa();
        }
        return soma;
    }

    public Optional<CentroDeCusto> verificarCentroDeCustoExistente(Long centroDeCustoId) {
        return centroDeCustoRepository.findById(centroDeCustoId);
    }

    public void verificacoesCCpagantes(List<CCPagantesInputDTO> ccPagantesInputDTOS) {
        Long projectIdToVerifications = ccPagantesInputDTOS.get(0).getProjeto().getId();
        if (calcularTaxa(ccPagantesInputDTOS) != 100) {
            projetoRepository.delete(projetoRepository.findByIdProjeto(projectIdToVerifications));
            throw new NegocioException("Verifique os valores da taxa (Não é igual a 100)");
        }
        if (verificarCentroDeCustoExistente(projectIdToVerifications).isPresent()) {
            projetoRepository.delete(projetoRepository.findByIdProjeto(projectIdToVerifications));
            throw new NegocioException("Verifique os centros de custos informados (ID Nao encontrado)");
        }
    }

}
