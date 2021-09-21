package net.weg.gestor.domain.service;


import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.ProjetoAssembler;
import net.weg.gestor.api.model.BaseDashboardConcluidosDTO;
import net.weg.gestor.api.model.ProjetoDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjetoInputDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.model.Secao;
import net.weg.gestor.domain.model.StatusProjeto;
import net.weg.gestor.domain.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    private CCPagantesService ccPagantesService;
    private ConsultoresAlocadosService consultoresAlocadosService;
    private SecaoService secaoService;
    private ConsultoresAlocadosRepository consultoresAlocadosRepository;

    public List<ProjetoDTO> listartodos(Long secaoId) {
        Secao secao = secaoRepository.findByIdAux(secaoId);
        return projetoAssembler.toCollectionModel(secaoService.listarCards(secao));
    }

    // brayan fazendo cagadinhas
    public List<ProjetoDTO> listartodos2(Long secaoId, LocalDate data) {
        Secao secao = secaoRepository.findByIdAux(secaoId);
        return projetoAssembler.toCollectionModel(secaoService.listarCardsTeste(secao, data));
    }

    public List<ProjetoDTO> listartodosstatus(Long secaoId, int typeStatus) {
        Secao secao = secaoRepository.findByIdAux(secaoId);
        return projetoAssembler.toCollectionModel(secaoService.listarCardsStatus(secao, typeStatus));
    }

    public ProjetoDTO listarPorId(Long projetoID){
        if (!projetoRepository.existsById(projetoID)){
            throw new NegocioException("Não existe um projeto com esse Id");
        }
        return projetoAssembler.toModel(projetoRepository.findByIdProjeto(projetoID));
    }

    public List<ProjetoDTO> listarStatus(int typeStatus){
        StatusProjeto status = verificationsService.returnTypeStatus(typeStatus);
        return projetoAssembler.toCollectionModel(projetoRepository.findByStatus(status));
    }

    public Projeto saveProject(ProjetoInputDTO projeto){
        Projeto projeto1 = projetoAssembler.toEntity(projeto);
        projeto1.setDataCadastro(LocalDate.now());
        projeto1.setHorasPrevistas(0);
        projeto1.setValor(0);
        projeto1.setHorasTrabalhadas(0);
        projeto1.setValorUtilizado(0);
        projeto1.setStatus(StatusProjeto.NAO_INICIADO);
        projetoRepository.save(projeto1);

        return projeto1;
    }

    public String cadastrar(ProjetoInputDTO projeto) {
        int taxa = 0;
        for (int i = 0; i < projeto.getCcpagantes().size(); ++i) {
            if (!secaoRepository.existsById(projeto.getCcpagantes().get(i).getSecoes_id())) {
               throw new NegocioException("ID Do " + (i+1) + "° CCPagante informado não foi encontrado");
            }
            taxa += projeto.getCcpagantes().get(i).getTaxa();
        }
        if (taxa != 100) {
            throw new NegocioException("Verifique os valores de taxa informados (Não é igual a 100)");
        }
        for (int i = 0; i < projeto.getConsultores().size(); ++i) {
            if (!usuarioRepository.existsById(projeto.getConsultores().get(i).getUsuarios_id())) {
                throw new NegocioException("ID Do " + (i+1) + "° Consultor informado não foi encontrado");
            }
        }
        Projeto projetoSalvo = saveProject(projeto);
        ccPagantesService.saveCcPagantes(projeto, projetoSalvo.getId());
        consultoresAlocadosService.saveConsultoresAlocados(projeto, projetoSalvo.getId());
        return "Projeto cadastrado";
    }

    public ArrayList<ProjetoDTO> findNoAllocateds(Long usuarioId) {
        ArrayList<ProjetoDTO> projetos = new ArrayList<>();
        List<Projeto> projetosCadastrados = projetoRepository.findAll();
        for (int i = 0; i < projetosCadastrados.size(); ++i) {
            if (consultoresAlocadosRepository.exists(usuarioId, projetosCadastrados.get(i).getId()).isEmpty()) {
                projetos.add(projetoAssembler.toModel(projetoRepository.findByIdProjeto(projetosCadastrados.get(i).getId())));
            }
        }
        return projetos;
    }

    public void editarAtrasado(Long idDoProjeto){
        if(!projetoRepository.existsById(idDoProjeto)) {
            throw new NegocioException("Verifique o id do projeto informado");
        }
        Projeto projeto = projetoRepository.findByIdProjeto(idDoProjeto);
        projeto.setStatus(StatusProjeto.ATRASADO);
        projetoRepository.save(projeto);
    }

    public void editarConcluida(Long idDoProjeto){
        if(!projetoRepository.existsById(idDoProjeto)) {
            throw new NegocioException("Verifique o id do projeto informado");
        }
        Projeto projeto = projetoRepository.findByIdProjeto(idDoProjeto);
        projeto.setStatus(StatusProjeto.CONCLUIDO);
        projeto.setDataFinalizacao(LocalDate.now());
        projetoRepository.save(projeto);
    }

    public void editarAndamento(Long idDoProjeto){
        if(!projetoRepository.existsById(idDoProjeto)) {
            throw new NegocioException("Verifique o id do projeto informado");
        }
        Projeto projeto = projetoRepository.findByIdProjeto(idDoProjeto);
        projeto.setStatus(StatusProjeto.EM_ANDAMENTO);
        projeto.setDataFinalizacao(LocalDate.now());
        projetoRepository.save(projeto);
    }

    public void iniciarProjeto(Long idDoProjeto) {
        if (!projetoRepository.existsById(idDoProjeto)) {
            throw new NegocioException("Verifique o id do projeto informado");
        }

        Projeto projeto = projetoRepository.findByIdProjeto(idDoProjeto);
        if (!projeto.getStatus().equals(StatusProjeto.NAO_INICIADO)) {
            throw new NegocioException("Não é possivel iniciar um projeto ja iniciado");
        }

        projeto.setStatus(StatusProjeto.EM_ANDAMENTO);
        projeto.setDataInicio(LocalDate.now());
        projetoRepository.save(projeto);
    }



    public int countProjetosConcluidos(Long secaoId, LocalDate data) {
        int quantidadeConcluidos = 0;
        List<Projeto> projetos =  secaoService.listarCards(secaoRepository.findByIdAux(secaoId));
        for (Projeto projeto : projetos) {
            if (projeto.getDataFinalizacao() != null) {
                if (projeto.getDataFinalizacao().isEqual(data)) {
                    quantidadeConcluidos++;
                }
            }
        }
        return quantidadeConcluidos;
    }


}
