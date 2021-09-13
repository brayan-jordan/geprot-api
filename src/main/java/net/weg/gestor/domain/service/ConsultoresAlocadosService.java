package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.ConsultoresAlocadosAssembler;
import net.weg.gestor.api.model.AlocarConsultorInputDTO;
import net.weg.gestor.api.model.ConsultorDTO;
import net.weg.gestor.api.model.UsuarioDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjetoInputDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.ConsultoresAlocados;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.model.Usuario;
import net.weg.gestor.domain.repository.ConsultoresAlocadosRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

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
        List<ConsultoresAlocados> consultores = consultoresAlocadosAssembler.toCollectionEntity(project.getConsultores());
        for (int i = 0; i < consultores.size(); ++i) {
            consultores.get(i).setProjetos_id(idCadastrado);
        }

        consultoresAlocadosRepository.saveAll(consultores);
    }

    public ConsultorDTO alocarConsultor(AlocarConsultorInputDTO alocarConsultorInputDTO) {
        if (!usuarioRepository.existsById(alocarConsultorInputDTO.getUsuarios_id())
                || !projetoRepository.existsById(alocarConsultorInputDTO.getProjetos_id())) {
            throw new NegocioException("Verifique os valores de ProjetoId e UsuarioId informados");
        }

        UsuarioDTO usuario = usuarioService.buscar(alocarConsultorInputDTO.getUsuarios_id());
        if (!usuario.getPermissao().equals("ROLE_CONSULTOR")) {
            throw new NegocioException("O usuário que você está tentando alocar não é um consultor");
        }

        Projeto projeto = projetoRepository.findByIdProjeto(alocarConsultorInputDTO.getProjetos_id());
        projeto.setValor(projeto.getValor() + (usuario.getPrecoHora() * alocarConsultorInputDTO.getLimiteHoras()));
        projeto.setHorasPrevistas(projeto.getHorasPrevistas() + alocarConsultorInputDTO.getLimiteHoras());
        projetoRepository.save(projeto);

        ConsultoresAlocados newConsultor = consultoresAlocadosAssembler.toEntity(alocarConsultorInputDTO);
        consultoresAlocadosRepository.save(newConsultor);
        return consultoresAlocadosAssembler.toModel(newConsultor);

    }

}
