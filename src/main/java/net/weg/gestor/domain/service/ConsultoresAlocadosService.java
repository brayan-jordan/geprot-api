package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.ConsultoresAlocadosAssembler;
import net.weg.gestor.api.model.AlocarConsultorInputDTO;
import net.weg.gestor.api.model.ConsultorDTO;
import net.weg.gestor.api.model.UsuarioDTO;
import net.weg.gestor.api.model.projetoinputDTO.AlocarConsultoresInputDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjetoInputDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.ConsultoresAlocados;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.model.Usuario;
import net.weg.gestor.domain.repository.ConsultoresAlocadosRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ConsultoresAlocadosService {

    private ConsultoresAlocadosRepository consultoresAlocadosRepository;
    private ConsultoresAlocadosAssembler consultoresAlocadosAssembler;
    private ProjetoRepository projetoRepository;
    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;

    public void saveConsultoresAlocados(ProjetoInputDTO project, Long idCadastrado) {
        for (int i = 0; i < project.getConsultores().size(); ++i) {
            AlocarConsultorInputDTO alocar = new AlocarConsultorInputDTO();
            alocar.setProjetos_id(idCadastrado);
            alocar.setLimiteHoras(project.getConsultores().get(i).getLimiteHoras());
            alocar.setUsuarios_id(project.getConsultores().get(i).getUsuarios_id());
            alocarConsultor(alocar);
        }
    }

    public ConsultorDTO alocarConsultor(AlocarConsultorInputDTO alocarConsultorInputDTO) {
        Long usuarioId = alocarConsultorInputDTO.getUsuarios_id();
        Long projetoId = alocarConsultorInputDTO.getProjetos_id();
        if (!usuarioRepository.existsById(usuarioId) || !projetoRepository.existsById(projetoId)) {
            throw new NegocioException("Verifique os valores de ProjetoId e UsuarioId informados");
        }

        if (consultoresAlocadosRepository.existsVerify(usuarioId, projetoId).isPresent()) {
            throw new NegocioException("Esse usuario já esta alocado no projeto");
        }

        UsuarioDTO usuario = usuarioService.buscar(usuarioId);
        if (!usuario.getPermissao().equals("ROLE_CONSULTOR")) {
            throw new NegocioException("O usuário que você está tentando alocar não é um consultor");
        }

        Projeto projeto = projetoRepository.findByIdProjeto(projetoId);
        projeto.setValor(projeto.getValor() + (usuario.getPrecoHora() * alocarConsultorInputDTO.getLimiteHoras()));
        projeto.setHorasPrevistas(projeto.getHorasPrevistas() + alocarConsultorInputDTO.getLimiteHoras());
        projetoRepository.save(projeto);

        ConsultoresAlocados newConsultor = consultoresAlocadosAssembler.toEntity(alocarConsultorInputDTO);
        consultoresAlocadosRepository.save(newConsultor);
        return consultoresAlocadosAssembler.toModel(newConsultor);

    }

}
