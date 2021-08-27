package net.weg.gestor.domain.service;


import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.ProjetoAssembler;
import net.weg.gestor.api.model.ProjetoInteiroDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjetoInteiroInputDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.model.StatusProjeto;
import net.weg.gestor.domain.repository.UsuarioRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.api.model.ProjetoDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjetoInputDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Service
@AllArgsConstructor
public class ProjetoService {

    private ConvertsService convertsService;
    private ValidationsService validationsService;
    private ProjetoRepository projetoRepository;
    private UsuarioRepository usuarioRepository;
    private ProjetoAssembler projetoAssembler;
    private CCPagantesService ccPagantesService;

    public ArrayList<ProjetoInteiroDTO> listartodos() {
        return convertsService.convertProjectList(projetoRepository.findAll());
    }

    public ArrayList<ProjetoInteiroDTO> listarStatus(int typeStatus){
        StatusProjeto status = validationsService.returnTypeStatus(typeStatus);
        return convertsService.convertProjectList(projetoRepository.findByStatusProjeto(status));
    }

    public ProjetoDTO cadastrar(ProjetoInputDTO projeto){
       if (!validationsService.verificaUsuarioExiste(projeto.getUsuario().getId()).isPresent()) {
           throw new NegocioException("Nao foi possivel cadastrar o projeto, faça o login e tente novamente");
       }

        Projeto projeto1 = projetoAssembler.toEntity(projeto);
        projeto1.setDatacadastro(LocalDateTime.now());
        projeto1.setHorastrabalhadas(0);
        projeto1.setValorutilizado(0);
        projeto1.setValorrestante(projeto.getValor());
        projeto1.setStatus(StatusProjeto.NAO_INICIADO);
        projeto1.setUsuario(usuarioRepository.findByIdUsuario(projeto1.getUsuario().getId()));
        projetoRepository.save(projeto1);

        return projetoAssembler.toModel(projeto1);
    }

    public ProjetoInteiroDTO cadastrar(ProjetoInteiroInputDTO projeto) {
        Long idCadastrado = cadastrar(projeto.getProjeto()).getId();
        for (int i = 0; i < projeto.getCcpagantes().size(); ++i) {
            projeto.getCcpagantes().get(i).getProjeto().setId(idCadastrado);
        }
        ccPagantesService.cadastrar(projeto.getCcpagantes());
        return convertsService.convertProject(idCadastrado);
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
        projeto.setDatafinalizacao(LocalDateTime.now());
        projetoRepository.save(projeto);
    }

    public void editarAndamento(Long idDoProjeto){
        if(validationsService.verificaProjetoExistente(idDoProjeto).isEmpty()) {
            throw new NegocioException("Verifique o id do projeto informado");
        }
        Projeto projeto = projetoRepository.findByIdProjeto(idDoProjeto);
        projeto.setStatus(StatusProjeto.EM_ANDAMENTO);
        projeto.setDatafinalizacao(LocalDateTime.now());
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
        projeto.setDatainicio(LocalDateTime.now());
        projetoRepository.save(projeto);
    }
}
