package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.CCPagantesAssembler;
import net.weg.gestor.api.model.CCPaganteDTO;
import net.weg.gestor.domain.entities.CCPagantes;
import net.weg.gestor.domain.entities.Projeto;
import net.weg.gestor.domain.entities.Secao;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.SecaoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CCPagantesService {

    private CCPagantesAssembler ccPagantesAssembler;
    private CCPagantesRepository ccPagantesRepository;
    private ProjetoRepository projetoRepository;
    private SecaoRepository secaoRepository;



    public List<CCPaganteDTO> buscarCCpagantesProjeto(Long projetoId) {
        Projeto projeto = projetoRepository.findById(projetoId).
                orElseThrow(() -> new NegocioException("Nao existe projeto com esse ID"));

//      Passando a lista dos ccpagantes diretamente para o método que fara o retorno, evitando armazenar dados em cache que
//        atrasam o fluxo do sistema
        return calcularValoresPagante(ccPagantesAssembler.toCollectionModel(
                ccPagantesRepository.buscarCCpagantesProjeto(projeto)),
                projeto.getValor()
        );
    }

    public List<CCPaganteDTO> calcularValoresPagante(List<CCPaganteDTO> ccPagantes, double valorProjeto) {
        ccPagantes.forEach(ccPaganteProjeto -> {
//            O valor de CC Pagante está vindo do método anterior com a taxa que o mesmo paga, assim economizando linhas de código
//            apesar de deixar menos legivel
            ccPaganteProjeto.setValorPagante(valorProjeto * 100 / ccPaganteProjeto.getValorPagante());
        });
        return ccPagantes;
    }

    public List<CCPagantes> buscarPorSecao(Long secaoId) {
        Secao secao = secaoRepository.findById(secaoId).orElseThrow(() -> new NegocioException("Essa secao nao paga nenhum projeto"));
        return ccPagantesRepository.findBySecao(secao);
    }

    public List<CCPagantes> buscarPorSecaoAndProjeto(Long secaoId, Long projetoId) {
        Secao secao = secaoRepository.findById(secaoId).orElseThrow(() -> new NegocioException("Nao existe uma secao com esse ID"));
        Projeto projeto = projetoRepository.findById(projetoId).orElseThrow(() -> new NegocioException("Nao existe projeto com esse ID"));
        return ccPagantesRepository.findBySecaoAndProjeto(secao, projeto);
    }


}
