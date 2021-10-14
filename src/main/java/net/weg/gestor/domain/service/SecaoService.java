package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.SecaoAssembler;
import net.weg.gestor.api.map.UsuarioAssembler;
import net.weg.gestor.api.model.SecaoDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.SecaoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SecaoService {

    private SecaoRepository secaoRepository;
    private VerificationsService verificationsService;
    private UsuarioRepository usuarioRepository;
    private UsuarioAssembler usuarioAssembler;
    private ProjetoRepository projetoRepository;
    private CCPagantesRepository ccPagantesRepository;
    private SecaoAssembler secaoAssembler;

    public SecaoDTO buscar(Long secaoId) {
        return secaoAssembler.toModel(secaoRepository.findById(secaoId).orElseThrow(
            () -> new NegocioException("Secao nao encontrada")));
    }

    public List<SecaoDTO> listarTodas() {
        return secaoAssembler.toCollectionModel(secaoRepository.findAll());
    }

    public SecaoDTO buscarSecao(long secaoId) {
        return secaoAssembler.toModel(secaoRepository.findById(secaoId).orElseThrow(() -> new NegocioException("Seção não encontrada")));
    }

//    @Transactional
//    public List<Secao> listar() {
//        return secaoRepository.findAll();
//    }
//
//    public UsuarioDTO editarSecao(long usuarioId, SecaoInputDTO secaoInputDTO) {
//        if (!usuarioRepository.existsById(usuarioId)){
//            throw new NegocioException("Não existe um usuario com esse ID");
//        }
//        if (!secaoRepository.existsById(secaoInputDTO.getId())){
//            throw new NegocioException("Não existe uma seção com esse ID");
//        }
//        Usuario usuario = usuarioRepository.findByIdUsuario(usuarioId);
//        usuario.setSecao(secaoRepository.findByIdAux(secaoInputDTO.getId()));
//        return usuarioAssembler.toModel(usuarioRepository.save(usuario));
//
//    }

//    public DashboardSecaoDTO listarDashboard(Long secaoId) {
//        DashboardSecaoDTO dashboardSecaoDTO = new DashboardSecaoDTO();
//        Secao secao = secaoRepository.findByIdAux(secaoId);
//        dashboardSecaoDTO.setProjetosAtrasados(this.porcentoStatus(StatusProjeto.ATRASADO, secao));
//        dashboardSecaoDTO.setProjetosEmAndamento(this.porcentoStatus(StatusProjeto.EM_ANDAMENTO, secao));
//        dashboardSecaoDTO.setProjetosConcluidos(this.porcentoStatus(StatusProjeto.CONCLUIDO, secao));
//        dashboardSecaoDTO.setProjetosNaoIniciados(this.porcentoStatus(StatusProjeto.NAO_INICIADO, secao));
//        dashboardSecaoDTO.setVerbasAprovadas(secaoRepository.findByIdAux(secaoId).getVerba());
//        dashboardSecaoDTO.setVerbasDisponivel(dashboardSecaoDTO.getVerbasAprovadas() - this.SomaVerbaProjetos(secao));
//        dashboardSecaoDTO.setRestoProjetosAtrasados(100 - dashboardSecaoDTO.getProjetosAtrasados());
//        dashboardSecaoDTO.setRestoProjetosEmAndamento(100 - dashboardSecaoDTO.getProjetosEmAndamento());
//        dashboardSecaoDTO.setRestoProjetosNaoIniciados(100 - dashboardSecaoDTO.getProjetosNaoIniciados());
//        return dashboardSecaoDTO;
//    }

//    public double porcentoStatus (StatusProjeto statusProjeto, Secao secao) {
//        List<CCPagantes> ccPagantes = ccPagantesRepository.findByIdSecao(secao.getId());
//        float contador = 0;
//        for (int i = 0; i < ccPagantes.size(); i ++){
//            Projeto projeto = projetoRepository.findByIdProjeto(ccPagantes.get(i).getProjetos_id());
//            if(projetoRepository.findByStatusSecao(statusProjeto, projeto.getId()) != null){
//                contador++;
//            }
//        }
//        float tamanho = ccPagantes.size();
//        return (contador * 100) / tamanho;
//    }

//    public List<Projeto> listarCards(Secao secao){
//        List<CCPagantes> ccPagantes = ccPagantesRepository.findByIdSecao(secao.getId());
//        List<Projeto> projetos = new ArrayList<>();
//        for (int i = 0; i < ccPagantes.size(); i ++){
//            Projeto projeto = projetoRepository.findByIdProjeto(ccPagantes.get(i).getProjetos_id());
//            if(projeto.getId() != null){
//                projetos.add(projeto);
//            }
//        }
//        return projetos;
//    }

//    public List<Projeto> listarCardsStatus(Secao secao, int status){
//        StatusProjeto statusParaLista = verificationsService.returnTypeStatus(status);
//        List<CCPagantes> ccPagantes = ccPagantesRepository.findByIdSecao(secao.getId());
//        List<Projeto> projetos = new ArrayList<>();
//        for (int i = 0; i < ccPagantes.size(); i ++){
//            Projeto projeto = projetoRepository.findByIdProjeto(ccPagantes.get(i).getProjetos_id());
//            if(projeto.getId() != null && projeto.getStatus().equals(statusParaLista)){
//                projetos.add(projeto);
//            }
//        }
//        return projetos;
//    }

//    public List<Projeto> listarContaining(Secao secao, String busca, int typeStatus){
//        List<CCPagantes> ccPagantes = ccPagantesRepository.findByIdSecao(secao.getId());
//        List<Projeto> projetos = new ArrayList<>();
//        List<Projeto> containing = projetoRepository.findByNomeContaining(busca);
//        if (typeStatus == 0) {
//            for (int i = 0; i < containing.size(); i++) {
//                if (verificaSecao(ccPagantes, containing.get(i))) {
//                    projetos.add(containing.get(i));
//                }
//            }
//        } else {
//            StatusProjeto statusParaLista = verificationsService.returnTypeStatus(typeStatus);
//            for (int i = 0; i < containing.size(); i++) {
//                if (verificaSecao(ccPagantes, containing.get(i)) && containing.get(i).getStatus().equals(statusParaLista)) {
//                    projetos.add(containing.get(i));
//                }
//            }
//        }
//
//        return projetos;
//    }

//    public boolean verificaSecao(List<CCPagantes> ccPagantes, Projeto projeto) {
//        for (int i = 0; i < ccPagantes.size(); ++i) {
//            if (projeto.getId() == ccPagantes.get(i).getProjetos_id()) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public float SomaVerbaProjetos(Secao secao){
//        List<CCPagantes> ccPagantes = ccPagantesRepository.findByIdSecao(secao.getId());
//        float soma = 0;
//        for (int i = 0; i < ccPagantes.size(); i ++){
//            Projeto projeto = projetoRepository.findByIdProjeto(ccPagantes.get(i).getProjetos_id());
//            if(projeto.getId() != null){
//                soma += projeto.getValor();
//            }
//        }
//        return soma;
//    }

}
