package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.centrodecustoinputDTO.CCPagantesInputDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.CentroDeCusto;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.model.StatusProjeto;
import net.weg.gestor.domain.model.Usuario;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.CentroDeCustoRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ValidationsService {

    private ProjetoRepository projetoRepository;
    private CentroDeCustoRepository centroDeCustoRepository;
    private UsuarioRepository usuarioRepository;

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

    public Optional<Projeto> verificaProjetoExistente(Long projetoId) {
        return projetoRepository.findById(projetoId);
    }

    public Optional<Usuario> verificaUsuarioExiste(Long usuarioId) {
        return usuarioRepository.findById(usuarioId);
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

    public StatusProjeto returnTypeStatus(int var) {
        switch (var) {
            case 1:
                return StatusProjeto.EM_ANDAMENTO;
            case 2:
                return StatusProjeto.ATRASADO;
            case 3:
                return StatusProjeto.CONCLUIDO;
            case 4:
                return StatusProjeto.NAO_INICIADO;
            default:
                throw new NegocioException("Erro (verifique o typeStatus informado)");
        }
    }

}
