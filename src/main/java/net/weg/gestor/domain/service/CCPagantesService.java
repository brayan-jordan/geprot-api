package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.CCPagantesAssembler;
import net.weg.gestor.domain.entities.CCPagantes;
import net.weg.gestor.domain.entities.Projeto;
import net.weg.gestor.domain.entities.Secao;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.SecaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CCPagantesService {

    private CCPagantesAssembler ccPagantesAssembler;
    private CCPagantesRepository ccPagantesRepository;
    private ProjetoRepository projetoRepository;
    private SecaoRepository secaoRepository;

    public List<CCPagantes> listarPorSecao(Long secaoId) {
        Secao secao = secaoRepository.findById(secaoId).orElseThrow(() -> new NegocioException("Essa secao nao paga nenhum projeto"));
        return ccPagantesRepository.findBySecao(secao);
    }

    public List<CCPagantes> listarPorSecaoAndProjeto(Long secaoId, Long projetoId) {
        Secao secao = secaoRepository.findById(secaoId).orElseThrow(() -> new NegocioException("Nao existe uma secao com esse ID"));
        Projeto projeto = projetoRepository.findById(projetoId).orElseThrow(() -> new NegocioException("Nao existe projeto com esse ID"));
        return ccPagantesRepository.findBySecaoAndProjeto(secao, projeto);
    }

}
