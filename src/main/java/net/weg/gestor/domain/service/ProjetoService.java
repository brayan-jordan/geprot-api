package net.weg.gestor.domain.service;


import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.ProjetoAssembler;
import net.weg.gestor.api.model.ProjetoCardDTO;
import net.weg.gestor.api.model.ProjetoDetalhadoDTO;
import net.weg.gestor.api.model.input.projetoinput.AlocarCCPagantesInputDTO;
import net.weg.gestor.api.model.input.projetoinput.ProjetoInputDTO;
import net.weg.gestor.domain.entities.CCPagantes;
import net.weg.gestor.domain.entities.Projeto;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjetoService {

    private ProjetoRepository projetoRepository;
    private UsuarioRepository usuarioRepository;
    private VerificationsService verificationsService;
    private ProjetoAssembler projetoAssembler;
    private SecaoRepository secaoRepository;
    private ConsultorRepository consultorRepository;
    private CCPagantesRepository ccPagantesRepository;
    private CCPagantesService ccPagantesService;
    private ConsultoresAlocadosService consultoresAlocadosService;
    private SecaoService secaoService;
    private ConsultoresAlocadosRepository consultoresAlocadosRepository;

    public List<ProjetoCardDTO> listarPorSecao(Long secaoId) {
        List<CCPagantes> secoesPagantes = ccPagantesService.listarPorSecao(secaoId);
        List<Projeto> projetos = new ArrayList<>();
        secoesPagantes.forEach(secao -> {
            projetos.add(projetoRepository.findById(secao.getProjeto().getId()).orElseThrow(
                () -> new NegocioException("Projeto nao encontrado")));
        });
        return projetoAssembler.toCollectionModel(projetos);

    }

    public ProjetoDetalhadoDTO buscarProjeto(Long secaoId ,Long projetoId) {
        return projetoAssembler.toModelDetalhada(
                projetoRepository.findById(projetoId).orElseThrow(() -> new NegocioException("Projeto nao encontrado")),
                ccPagantesService.listarPorSecaoAndProjeto(secaoId, projetoId)
        );
    }

    public String cadastrarProjeto(ProjetoInputDTO projeto) {
        projetoValidations(projeto);
        return "Falta fazer cadastrar o projeto :)";
    }

    public void projetoValidations(ProjetoInputDTO projeto) {
        if (projeto.getCcpagantes().size() == 0) {
            throw new NegocioException("Voce nao alocou nenhum centro de custo pagante");
        }

        if (projeto.getConsultores().size() == 0) {
            throw new NegocioException("Voce nao alocou nenhum consultor");
        }

        int taxa = projeto.getCcpagantes().stream().mapToInt(AlocarCCPagantesInputDTO::getTaxa).sum();

        if (taxa != 100) {
            throw new NegocioException("Taxa é diferente de 100%");
        }

        projeto.getConsultores().forEach(consultorInputDTO -> {
            if (!consultorRepository.existsById(consultorInputDTO.getId())) {
                throw new NegocioException("Nao existe um consultor com algum ID informado");
            }
        });

    }


}
