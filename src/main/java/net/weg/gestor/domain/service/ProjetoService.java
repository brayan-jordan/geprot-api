package net.weg.gestor.domain.service;


import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.ProjetoAssembler;
import net.weg.gestor.api.model.ProjetoDTO;
import net.weg.gestor.api.model.projetoinputDTO.AlocarConsultoresInputDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjetoInputDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.model.Secao;
import net.weg.gestor.domain.model.StatusProjeto;
import net.weg.gestor.domain.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjetoService {

    private ProjetoRepository projetoRepository;
    private UsuarioRepository usuarioRepository;
    private ProjetoAssembler projetoAssembler;
    private SecaoRepository secaoRepository;
    private CCPagantesService ccPagantesService;
    private ConsultoresAlocadosService consultoresAlocadosService;
    private SecaoService secaoService;

    public List<ProjetoDTO> listartodos(Long secaoId) {
        Secao secao = secaoRepository.findByIdAux(secaoId);
        return projetoAssembler.toCollectionModel(secaoService.listarCards(secao));
    }

    public ProjetoDTO listarPorId(Long projetoID){
        if (!projetoRepository.existsById(projetoID)){
            throw new NegocioException("Não existe um projeto com esse Id");
        }
        return projetoAssembler.toModel(projetoRepository.findByIdProjeto(projetoID));
    }

    public List<ProjetoDTO> listarStatus(int typeStatus){
        StatusProjeto status = returnTypeStatus(typeStatus);
        return projetoAssembler.toCollectionModel(projetoRepository.findByStatus(status));
    }

    public Projeto saveProject(ProjetoInputDTO projeto){
        Projeto projeto1 = projetoAssembler.toEntity(projeto);
        projeto1.setDataCadastro(LocalDate.now());
        projeto1.setHorasPrevistas(calcularHorasPrevistas(projeto.getConsultores()));
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

    private StatusProjeto returnTypeStatus(int typeStatus) {
        switch (typeStatus) {
            case 1:
                return StatusProjeto.NAO_INICIADO;
            case 2:
                return StatusProjeto.ATRASADO;
            case 3:
                return StatusProjeto.CONCLUIDO;
            case 4:
                return StatusProjeto.EM_ANDAMENTO;
            default:
                throw new NegocioException("Numero invalido");
        }
    }

    public int calcularHorasPrevistas(List<AlocarConsultoresInputDTO> consultores) {
        int var = 0;
        for (int i = 0; i < consultores.size(); ++i) {
            var += consultores.get(i).getLimiteHoras();
        }
        return var;
    }
}
