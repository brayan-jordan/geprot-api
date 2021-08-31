package net.weg.gestor.domain.service;


import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.ProjetoAssembler;
import net.weg.gestor.api.model.ProjetoDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjectInputDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.model.StatusProjeto;
import net.weg.gestor.domain.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjetoService {

    private ConvertsService convertsService;
    private ValidationsService validationsService;
    private ProjetoRepository projetoRepository;
    private UsuarioRepository usuarioRepository;
    private CCPagantesRepository ccPagantesRepository;
    private ProjetoAssembler projetoAssembler;
    private CentroDeCustoRepository centroDeCustoRepository;
    private CCPagantesService ccPagantesService;
    private ConsultoresAlocadosRepository consultoresAlocadosRepository;
    private ConsultoresAlocadosService consultoresAlocadosService;

    public List<ProjetoDTO> listartodos() {
        return projetoAssembler.toCollectionModel(projetoRepository.findAll());
    }

//    public ArrayList<ProjetoInteiroDTO> listarStatus(int typeStatus){
//        StatusProjeto status = validationsService.returnTypeStatus(typeStatus);
//        return convertsService.convertProjectList(projetoRepository.findByStatusProjeto(status));
//    }

    public Projeto saveProject(ProjectInputDTO projeto){
        Projeto projeto1 = projetoAssembler.toEntity(projeto);
        projeto1.setDataCadastro(LocalDateTime.now());
        projeto1.setHorasTrabalhadas(0);
        projeto1.setValorUtilizado(0);
        projeto1.setStatus(StatusProjeto.NAO_INICIADO);
        projetoRepository.save(projeto1);

        return projeto1;
    }

    public String cadastrar(ProjectInputDTO projeto) {
        int taxa = 0;
        for (int i = 0; i < projeto.getCcpagantes().size(); ++i) {
            if (!centroDeCustoRepository.existsById(projeto.getCcpagantes().get(i).getCentros_de_custo_id())) {
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
        Long idCadastrado = saveProject(projeto).getId();
        ccPagantesService.saveCcPagantes(projeto, idCadastrado);
        consultoresAlocadosService.saveConsultoresAlocados(projeto, idCadastrado);
        return "Deu boa";
    }

    public void editarAtrasado(Long idDoProjeto){
        if(validationsService.verificaProjetoExistente(idDoProjeto).isEmpty()) {
            throw new NegocioException("Verifique o id do projeto informado");
        }
        Projeto projeto = projetoRepository.findByIdProjeto(idDoProjeto);
        projeto.setStatus(StatusProjeto.ATRASADO);
        projetoRepository.save(projeto);
    }

    public void editarConcluida(Long idDoProjeto){
        if(validationsService.verificaProjetoExistente(idDoProjeto).isEmpty()) {
            throw new NegocioException("Verifique o id do projeto informado");
        }
        Projeto projeto = projetoRepository.findByIdProjeto(idDoProjeto);
        projeto.setStatus(StatusProjeto.CONCLUIDO);
        projeto.setDataFinalizacao(LocalDateTime.now());
        projetoRepository.save(projeto);
    }

    public void editarAndamento(Long idDoProjeto){
        if(validationsService.verificaProjetoExistente(idDoProjeto).isEmpty()) {
            throw new NegocioException("Verifique o id do projeto informado");
        }
        Projeto projeto = projetoRepository.findByIdProjeto(idDoProjeto);
        projeto.setStatus(StatusProjeto.EM_ANDAMENTO);
        projeto.setDataFinalizacao(LocalDateTime.now());
        projetoRepository.save(projeto);
    }

    public void iniciarProjeto(Long projetoId) {
        if (!validationsService.verificaProjetoExistente(projetoId).isPresent()) {
            throw new NegocioException("Verifique o id do projeto informado");
        }

        Projeto projeto = projetoRepository.findByIdProjeto(projetoId);
        if (!projeto.getStatus().equals(StatusProjeto.NAO_INICIADO)) {
            throw new NegocioException("Não é possivel iniciar um projeto ja iniciado");
        }

        projeto.setStatus(StatusProjeto.EM_ANDAMENTO);
        projeto.setDataInicio(LocalDateTime.now());
        projetoRepository.save(projeto);
    }
}
