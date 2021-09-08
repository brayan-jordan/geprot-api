package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.UsuarioAssembler;
import net.weg.gestor.api.model.DashboardSecaoDTO;
import net.weg.gestor.api.model.UsuarioDTO;
import net.weg.gestor.api.model.usuarioinputDTO.SecaoInputDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.*;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.SecaoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SecaoService {

    private SecaoRepository secaoRepository;
    private UsuarioRepository usuarioRepository;
    private UsuarioAssembler usuarioAssembler;
    private ProjetoRepository projetoRepository;
    private CCPagantesRepository ccPagantesRepository;

    @Transactional
    public Secao buscar(Long secaoId) {
        return secaoRepository.findById(secaoId).
                orElseThrow(() -> new NegocioException("Nao foi encontrado uma seção com esse ID"));
    }

    @Transactional
    public List<Secao> listar() {
        return secaoRepository.findAll();
    }

    public UsuarioDTO editarSecao(long usuarioId, SecaoInputDTO secaoInputDTO) {
        if (!usuarioRepository.existsById(usuarioId)){
            throw new NegocioException("Não existe um usuario com esse ID");
        }
        if (!secaoRepository.existsById(secaoInputDTO.getId())){
            throw new NegocioException("Não existe uma seção com esse ID");
        }
        Usuario usuario = usuarioRepository.findByIdUsuario(usuarioId);
        usuario.setSecao(secaoRepository.findByIdAux(secaoInputDTO.getId()));
        return usuarioAssembler.toModel(usuarioRepository.save(usuario));

    }

    public DashboardSecaoDTO listarDashboard(Long secaoId) {
        DashboardSecaoDTO dashboardSecaoDTO = new DashboardSecaoDTO();
        Secao secao = secaoRepository.findByIdAux(secaoId);
        dashboardSecaoDTO.setProjetosAtrasados(this.porcentoStatus(StatusProjeto.ATRASADO, secao));
        dashboardSecaoDTO.setProjetosEmAndamento(this.porcentoStatus(StatusProjeto.EM_ANDAMENTO, secao));
        dashboardSecaoDTO.setProjetosConcluidos(this.porcentoStatus(StatusProjeto.CONCLUIDO, secao));
        dashboardSecaoDTO.setProjetosNaoIniciados(this.porcentoStatus(StatusProjeto.NAO_INICIADO, secao));
        dashboardSecaoDTO.setVerbasAprovadas(secaoRepository.findByIdAux(secaoId).getVerba());
        dashboardSecaoDTO.setVerbasDisponivel(projetoRepository.findVerbaUtilizadaSecao(secaoId));
        return dashboardSecaoDTO;
    }

    public double porcentoStatus (StatusProjeto statusProjeto, Secao secao) {
        List<CCPagantes> ccPagantes = ccPagantesRepository.findByIdSecao(secao.getId());
        int contador = 0;
        for (int i = 0; i < ccPagantes.size(); i ++){
            Projeto projeto = projetoRepository.findByIdProjeto(ccPagantes.get(i).getProjetos_id());
            if(projetoRepository.findByStatusSecao(statusProjeto, projeto.getId()) != null){
                contador++;
            }
        }
        return contador * 100 / ccPagantes.size();
    }
}
