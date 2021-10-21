package net.weg.gestor.domain.service;


import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.ProjetoAssembler;
import net.weg.gestor.api.model.ProjetoAlocarDTO;
import net.weg.gestor.api.model.ProjetoCardDTO;
import net.weg.gestor.api.model.ProjetoDetalhadoDTO;
import net.weg.gestor.api.model.cadastrarprojetoinput.ProjetoCCPagantesInputDTO;
import net.weg.gestor.api.model.cadastrarprojetoinput.ProjetoInputDTO;
import net.weg.gestor.api.model.input.AlocarConsultorInputDTO;
import net.weg.gestor.domain.entities.CCPagantes;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.Projeto;
import net.weg.gestor.domain.entities.StatusProjeto;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class ProjetoService {

    private ProjetoRepository projetoRepository;
    private ProjetoAssembler projetoAssembler;
    private ConsultorRepository consultorRepository;
    private CCPagantesService ccPagantesService;
    private ConsultoresAlocadosService consultoresAlocadosService;

    private List<Projeto> buscarTodosProjetoSecao(Long secaoId) {
        List<CCPagantes> secoesPagantes = ccPagantesService.buscarPorSecao(secaoId);
        List<Projeto> projetos = new ArrayList<>();
        secoesPagantes.forEach(secao -> {
            projetos.add(projetoRepository.findById(secao.getProjeto().getId()).orElseThrow(
                    () -> new NegocioException("Projeto nao encontrado")));
        });
        return projetos;
    }

    public List<ProjetoCardDTO> listarPorSecao(Long secaoId) {
        return projetoAssembler.toCollectionModel(buscarTodosProjetoSecao(secaoId));
    }

    public List<ProjetoCardDTO> buscarPorNome(Long secaoId, String campoBusca) {
        List<Projeto> projetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        projetos.forEach(projeto -> {
            if (projeto.getNome().toLowerCase(Locale.ROOT).contains(campoBusca.toLowerCase(Locale.ROOT))) {
                projetosFiltrados.add(projeto);
            }
        });
        return projetoAssembler.toCollectionModel(projetosFiltrados);
    }

    public List<ProjetoCardDTO> buscarPorNomeResponsavel(Long secaoId, String campoBusca) {
        List<Projeto> projetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        projetos.forEach(projeto -> {
            if (projeto.getNomeResponsavel().toLowerCase(Locale.ROOT).contains(campoBusca.toLowerCase(Locale.ROOT))) {
                projetosFiltrados.add(projeto);
            }
        });
        return projetoAssembler.toCollectionModel(projetosFiltrados);
    }

    public List<ProjetoCardDTO> buscarPorNomeEStatus(Long secaoId, String campoBusca, int status) {
        List<CCPagantes> secoesPagantes = ccPagantesService.buscarPorSecao(secaoId);
        List<Projeto> projetos = new ArrayList<>();
        List<Projeto> projetosFiltrados = new ArrayList<>();
        StatusProjeto statusConvertido = convertFilter(status);
        secoesPagantes.forEach(secao -> {
            Projeto projeto = projetoRepository.findById(secao.getProjeto().getId()).orElseThrow(
                    () -> new NegocioException("Projeto nao encontrado"));
            if (projeto.getNome().toLowerCase(Locale.ROOT).contains(campoBusca.toLowerCase(Locale.ROOT)) &&
                    projeto.getStatus().equals(statusConvertido)
            ) {
                projetosFiltrados.add(projeto);
            }
        });
        return projetoAssembler.toCollectionModel(projetosFiltrados);
    }

    public List<ProjetoCardDTO> buscarPorStatus(Long secaoId, int status) {
        List<Projeto> projetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        StatusProjeto statusConvertido = convertFilter(status);
        projetos.forEach(projeto -> {
            if (projeto.getStatus().equals(statusConvertido)) {
                projetosFiltrados.add(projeto);
            }
        });
        return projetoAssembler.toCollectionModel(projetosFiltrados);
    }


    public List<ProjetoAlocarDTO> buscarProjetosConsultorNaoAlocado(Long consultorId, Long secaoId) {
        Consultor consultor = consultorRepository.findById(consultorId).orElseThrow(
                () -> new NegocioException("Consultor nao encontrado"));

        return projetoAssembler.toCollectionModelAlocado(buscarTodosProjetoSecao(secaoId), consultor);
    }

    public String cadastrarProjeto(ProjetoInputDTO projeto) {
        projetoValidations(projeto);
        Long projetoId = projetoRepository.save(projetoAssembler.toEntityCadastro(projeto)).getId();
        ccPagantesService.saveCCPagantesProjeto(projeto.getCcpagantes(), projetoId);
        projeto.getConsultores().forEach(consultor -> {
            consultoresAlocadosService.alocarConsultor(new AlocarConsultorInputDTO(
                    consultor.getConsultorId(), projetoId, consultor.getQuantidadeHoras()));
        });
        return "Falta fazer cadastrar o projeto :)";
    }

    public void projetoValidations(ProjetoInputDTO projeto) {
        if (projeto.getCcpagantes().size() == 0) {
            throw new NegocioException("Voce nao alocou nenhum centro de custo pagante");
        }

        if (projeto.getConsultores().size() == 0) {
            throw new NegocioException("Voce nao alocou nenhum consultor");
        }

        int taxa = projeto.getCcpagantes().stream().mapToInt(ProjetoCCPagantesInputDTO::getTaxa).sum();

        if (taxa != 100) {
            throw new NegocioException("Taxa é diferente de 100%");
        }

        projeto.getConsultores().forEach(consultorInputDTO -> {
            if (!consultorRepository.existsById(consultorInputDTO.getConsultorId())) {
                throw new NegocioException("Nao existe um consultor com algum ID informado");
            }
        });
    }

    public StatusProjeto convertFilter(int filtroInteiro) {
        switch (filtroInteiro) {
            case 1:
                return StatusProjeto.ATRASADO;
            case 2:
                return StatusProjeto.CONCLUIDO;
            case 3:
                return StatusProjeto.EM_ANDAMENTO;
            case 4:
                return StatusProjeto.NAO_INICIADO;
            default:
                return null;
        }
    }

    public ProjetoCardDTO buscarPorId(Long projetoId) {
        return projetoAssembler.toModel(projetoRepository.findById(projetoId).orElseThrow(() -> new NegocioException("Id inválido")));
    }
}
