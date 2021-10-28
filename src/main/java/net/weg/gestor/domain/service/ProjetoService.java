package net.weg.gestor.domain.service;


import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.ProjetoAssembler;
import net.weg.gestor.api.model.DashboardConcluidos;
import net.weg.gestor.api.model.projeto.ProjetoAlocarDTO;
import net.weg.gestor.api.model.projeto.ProjetoCardDTO;
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

import java.time.LocalDate;
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

    public List<Projeto> buscarTodosProjetoSecao(Long secaoId) {
        List<CCPagantes> ccPagantes = ccPagantesService.buscarPorSecao(secaoId);
        List<Projeto> projetos = new ArrayList<>();
        ccPagantes.forEach(ccPagante -> {
            projetos.add(projetoRepository.findById(ccPagante.getProjeto().getId()).orElseThrow(
                    () -> new NegocioException("Projeto nao encontrado")
            ));
        });
        return projetos;
    }

    public ProjetoCardDTO buscarPorId(Long projetoId) {
        return projetoAssembler.toModel(projetoRepository.findById(projetoId).orElseThrow(
                () -> new NegocioException("Id inválido")
        ));
    }

    public List<ProjetoCardDTO> listarPorSecao(Long secaoId) {
        return projetoAssembler.toCollectionModel(buscarTodosProjetoSecao(secaoId));
    }

    public List<ProjetoCardDTO> buscarPorNome(Long secaoId, String pesquisaPorNome) {
        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        todosProjetos.forEach(projeto -> {
            if (projeto.getNome().toLowerCase(Locale.ROOT).contains(pesquisaPorNome.toLowerCase(Locale.ROOT))) {
                projetosFiltrados.add(projeto);
            }
        });
        return projetoAssembler.toCollectionModel(projetosFiltrados);
    }

    public List<ProjetoCardDTO> buscarPorNomeResponsavel(Long secaoId, String pesquisaPorNomeResponsavel) {
        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        todosProjetos.forEach(projeto -> {
            if (projeto.getNomeResponsavel().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNomeResponsavel.toLowerCase(Locale.ROOT))
            ) {
                projetosFiltrados.add(projeto);
            }
        });
        return projetoAssembler.toCollectionModel(projetosFiltrados);
    }

    public List<ProjetoCardDTO> buscarPorId(Long secaoId, Long pesquisaPorId) {
        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        todosProjetos.forEach(projeto -> {
            if (projeto.getId().toString().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorId.toString().toLowerCase(Locale.ROOT))
            ) {
                projetosFiltrados.add(projeto);
            }
        });
        return projetoAssembler.toCollectionModel(projetosFiltrados);
    }

    public List<ProjetoCardDTO> buscarPorNomeENomeResponsavel(
            Long secaoId,
            String pesquisaPorNome,
            String pesquisaPorNomeResponsavel
    ) {
        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        todosProjetos.forEach(projeto -> {
            if (projeto.getNome().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNome.toLowerCase(Locale.ROOT)) &&
                projeto.getNomeResponsavel().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNomeResponsavel.toLowerCase(Locale.ROOT))
            ) {
                projetosFiltrados.add(projeto);
            }
        });
        return projetoAssembler.toCollectionModel(projetosFiltrados);
    }

    public List<ProjetoCardDTO> buscarPorNomeEId(
            Long secaoId,
            String pesquisaPorNome,
            Long pesquisaPorId
    ) {
        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        todosProjetos.forEach(projeto -> {
            if (projeto.getNome().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNome.toLowerCase(Locale.ROOT)) &&
                projeto.getId().toString().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorId.toString().toLowerCase(Locale.ROOT))
            ) {
                projetosFiltrados.add(projeto);
            }
        });
        return projetoAssembler.toCollectionModel(projetosFiltrados);
    }

    public List<ProjetoCardDTO> buscarPorNomeResponsavelEId(
            Long secaoId,
            String pesquisaPorNomeResponsavel,
            Long pesquisaPorId
    ) {
        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        todosProjetos.forEach(projeto -> {
            if (projeto.getNomeResponsavel().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNomeResponsavel.toLowerCase(Locale.ROOT)) &&
                projeto.getId().toString().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorId.toString().toLowerCase(Locale.ROOT))
            ) {
                projetosFiltrados.add(projeto);
            }
        });
        return projetoAssembler.toCollectionModel(projetosFiltrados);
    }

    public List<ProjetoCardDTO> buscarPorNomeNomeResponsavelEId(
            Long secaoId,
            String pesquisaPorNomeResponsavel,
            String pesquisaPorNome,
            Long pesquisaPorId
    ) {
        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        todosProjetos.forEach(projeto -> {
            if (projeto.getNomeResponsavel().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNomeResponsavel.toLowerCase(Locale.ROOT)) &&
                projeto.getId().toString().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorId.toString().toLowerCase(Locale.ROOT)) &&
                projeto.getNome().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNome.toLowerCase(Locale.ROOT))
            ) {
                projetosFiltrados.add(projeto);
            }
        });
        return projetoAssembler.toCollectionModel(projetosFiltrados);
    }



    public List<ProjetoCardDTO> buscarPorNomeEStatus(Long secaoId, String pesquisaPorNome, int status) {
        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        StatusProjeto statusConvertido = convertFilter(status);
        todosProjetos.forEach(projeto -> {
            if (projeto.getNome().toLowerCase(Locale.ROOT).contains(pesquisaPorNome.toLowerCase(Locale.ROOT)) &&
                    projeto.getStatus().equals(statusConvertido)
            ) {
                projetosFiltrados.add(projeto);
            }
        });
        return projetoAssembler.toCollectionModel(projetosFiltrados);
    }

    public List<ProjetoCardDTO> buscarPorStatus(Long secaoId, int status) {
        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        StatusProjeto statusConvertido = convertFilter(status);
        todosProjetos.forEach(projeto -> {
            if (projeto.getStatus().equals(statusConvertido)) {
                projetosFiltrados.add(projeto);
            }
        });
        return projetoAssembler.toCollectionModel(projetosFiltrados);
    }


    public List<ProjetoAlocarDTO> alocBuscarProjeto(Long consultorId, Long secaoId) {
        Consultor consultor = consultorRepository.findById(consultorId).orElseThrow(
                () -> new NegocioException("Consultor nao encontrado")
        );

        return projetoAssembler.toCollectionModelProjetosAlocar(buscarTodosProjetoSecao(secaoId), consultor);
    }

    public List<ProjetoAlocarDTO> alocBuscarProjetoComNomeResponsavel(
            Long consultorId,
            Long secaoId,
            String pesquisaPorNomeResponsavel
    ) {
        Consultor consultor = consultorRepository.findById(consultorId).orElseThrow(
                () -> new NegocioException("Consultor nao encontrado")
        );

        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        todosProjetos.forEach(projeto -> {
            if (projeto.getNomeResponsavel().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNomeResponsavel.toLowerCase(Locale.ROOT))
            ) {
                projetosFiltrados.add(projeto);
            }
        });

        return projetoAssembler.toCollectionModelProjetosAlocar(projetosFiltrados, consultor);
    }

    public List<ProjetoAlocarDTO> alocBuscarProjetoComId(
            Long consultorId,
            Long secaoId,
            String pesquisaPorId
    ) {
        Consultor consultor = consultorRepository.findById(consultorId).orElseThrow(
                () -> new NegocioException("Consultor nao encontrado")
        );

        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        todosProjetos.forEach(projeto -> {
            if (projeto.getId().toString().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorId.toLowerCase(Locale.ROOT))
            ) {
                projetosFiltrados.add(projeto);
            }
        });

        return projetoAssembler.toCollectionModelProjetosAlocar(projetosFiltrados, consultor);
    }

    public List<ProjetoAlocarDTO> alocBuscarProjetoComNomeProjeto(
            Long consultorId,
            Long secaoId,
            String pesquisaPorNomeProjeto
    ) {
        Consultor consultor = consultorRepository.findById(consultorId).orElseThrow(
                () -> new NegocioException("Consultor nao encontrado")
        );

        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        todosProjetos.forEach(projeto -> {
            if (projeto.getNome().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNomeProjeto.toLowerCase(Locale.ROOT))
            ) {
                projetosFiltrados.add(projeto);
            }
        });

        return projetoAssembler.toCollectionModelProjetosAlocar(projetosFiltrados, consultor);
    }

    public List<ProjetoAlocarDTO> alocBuscarProjetoComIdeNome(
            Long consultorId,
            Long secaoId,
            String pesquisaPorId,
            String pesquisaPorNomeProjeto
    ) {
        Consultor consultor = consultorRepository.findById(consultorId).orElseThrow(
                () -> new NegocioException("Consultor nao encontrado")
        );

        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        todosProjetos.forEach(projeto -> {
            if (projeto.getId().toString().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorId.toLowerCase(Locale.ROOT)) &&
                projeto.getNome().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNomeProjeto.toLowerCase(Locale.ROOT))
            ) {
                projetosFiltrados.add(projeto);
            }
        });

        return projetoAssembler.toCollectionModelProjetosAlocar(projetosFiltrados, consultor);
    }

    public List<ProjetoAlocarDTO> alocBuscarProjetoComIdeNomeResponsavel(
            Long consultorId,
            Long secaoId,
            String pesquisaPorId,
            String pesquisaPorNomeResponsavel
    ) {
        Consultor consultor = consultorRepository.findById(consultorId).orElseThrow(
                () -> new NegocioException("Consultor nao encontrado")
        );

        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        todosProjetos.forEach(projeto -> {
            if (projeto.getId().toString().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorId.toLowerCase(Locale.ROOT)) &&
                projeto.getNomeResponsavel().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNomeResponsavel.toLowerCase(Locale.ROOT))
            ) {
                projetosFiltrados.add(projeto);
            }
        });

        return projetoAssembler.toCollectionModelProjetosAlocar(projetosFiltrados, consultor);
    }

    public List<ProjetoAlocarDTO> alocBuscarProjetoComNomeeNomeResponsavel(
            Long consultorId,
            Long secaoId,
            String pesquisaPorNome,
            String pesquisaPorNomeResponsavel
    ) {
        Consultor consultor = consultorRepository.findById(consultorId).orElseThrow(
                () -> new NegocioException("Consultor nao encontrado")
        );

        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        todosProjetos.forEach(projeto -> {
            if (projeto.getNome().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNome.toLowerCase(Locale.ROOT)) &&
                projeto.getNomeResponsavel().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNomeResponsavel.toLowerCase(Locale.ROOT))
            ) {
                projetosFiltrados.add(projeto);
            }
        });

        return projetoAssembler.toCollectionModelProjetosAlocar(projetosFiltrados, consultor);
    }

    public List<ProjetoAlocarDTO> alocBuscarProjetoComNomeeNomeResponsaveleId(
            Long consultorId,
            Long secaoId,
            String pesquisaPorNome,
            String pesquisaPorNomeResponsavel,
            String pesquisaPorId
    ) {
        Consultor consultor = consultorRepository.findById(consultorId).orElseThrow(
                () -> new NegocioException("Consultor nao encontrado")
        );

        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<Projeto> projetosFiltrados = new ArrayList<>();
        todosProjetos.forEach(projeto -> {
            if (projeto.getNome().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNome.toLowerCase(Locale.ROOT)) &&
                projeto.getNomeResponsavel().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNomeResponsavel.toLowerCase(Locale.ROOT)) &&
                projeto.getId().toString().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorId.toLowerCase(Locale.ROOT))
            ) {
                projetosFiltrados.add(projeto);
            }
        });

        return projetoAssembler.toCollectionModelProjetosAlocar(projetosFiltrados, consultor);
    }

    public String cadastrarProjeto(ProjetoInputDTO projeto) {
//        Chama o método void que faz as verificações se é possível cadastrar esse projeto
        projetoValidations(projeto);

//        Salva o projeto e pega o ID para usar nas entidades fracas
        Long projetoId = projetoRepository.save(projetoAssembler.toEntityCadastro(projeto)).getId();

//        Pega a lista de cc pagantes e manda para um método próprio as salvar
        ccPagantesService.saveCCPagantesProjeto(projeto.getCcpagantes(), projetoId);

//        Pega consultor por consultor dos escolhidos e salva em consultores alocados
        projeto.getConsultores().forEach(consultor -> {
            consultoresAlocadosService.alocarConsultor(new AlocarConsultorInputDTO(
                    consultor.getConsultorId(),
                    projetoId,
                    consultor.getQuantidadeHoras()
                )
            );
        });
        return "Falta fazer cadastrar o projeto :)";
    }

    private void projetoValidations(ProjetoInputDTO projeto) {
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

    private StatusProjeto convertFilter(int filtroInteiro) {
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

//    Início das funções para listar projetos concluídos nos ultímos 7 dias
    private List<DashboardConcluidos> mapearUltimos7Dias() {
        List<DashboardConcluidos> ultimos7dias = new ArrayList<>();
        LocalDate date = LocalDate.now();
        for (int i = 0; i < 7; ++i) {
            date = date.minusDays(1);
            ultimos7dias.add(new DashboardConcluidos(date, 0));
        }
        return ultimos7dias;
    }

    private int converterParaODiaDaLista(LocalDate dataProjeto) {
        for (int i = 0; i < 7; ++i) {
            if (dataProjeto.isEqual(LocalDate.now().minusDays((i + 1)))) {
                return i;
            }
        }

        return 1000;
    }

    public List<DashboardConcluidos> concluidosUltimos7Dias(Long secaoId) {
        List<Projeto> todosProjetos = buscarTodosProjetoSecao(secaoId);
        List<DashboardConcluidos> ultimos7dias = mapearUltimos7Dias();
        todosProjetos.forEach(projeto -> {
            if (projeto.getDataFinalizacao() != null && !projeto.getDataFinalizacao().equals(LocalDate.now())) {
                if (projeto.getDataFinalizacao().isBefore(LocalDate.now()) && projeto.getDataFinalizacao().isAfter(LocalDate.now().minusDays(7))) {
                    ultimos7dias.get(converterParaODiaDaLista(projeto.getDataFinalizacao())).setQuantidadeConcluidos(
                    ultimos7dias.get(converterParaODiaDaLista(projeto.getDataFinalizacao())).getQuantidadeConcluidos() + 1);
                }
            }
        });

        return ultimos7dias;
    }

}
