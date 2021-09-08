package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.UsuarioAssembler;
import net.weg.gestor.api.model.DashboardSecaoDTO;
import net.weg.gestor.api.model.UsuarioDTO;
import net.weg.gestor.api.model.usuarioinputDTO.SecaoInputDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.Secao;
import net.weg.gestor.domain.model.StatusProjeto;
import net.weg.gestor.domain.model.Usuario;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.SecaoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SecaoService {

    private SecaoRepository secaoRepository;
    private UsuarioRepository usuarioRepository;
    private UsuarioAssembler usuarioAssembler;
    private ProjetoRepository projetoRepository;

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
        dashboardSecaoDTO.setProjetosAtrasados(projetoRepository.findByStatus(StatusProjeto.ATRASADO).size());
        dashboardSecaoDTO.setProjetosEmAndamento(projetoRepository.findByStatus(StatusProjeto.EM_ANDAMENTO).size());
        dashboardSecaoDTO.setProjetosConcluidos(projetoRepository.findByStatus(StatusProjeto.CONCLUIDO).size());
        dashboardSecaoDTO.setVerbasAprovadas(secaoRepository.findByIdAux(secaoId).getVerba());
        dashboardSecaoDTO.setVerbasDisponivel(projetoRepository.findVerbaUtilizadaSecao(secaoId));
        return dashboardSecaoDTO;
    }
}
