package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.ConsultoresAlocadosAssembler;
import net.weg.gestor.api.model.ConsultorAlocadoDTO;
import net.weg.gestor.api.model.ConsultorDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjectInputConsAlocDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjectInputDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.ConsultoresAlocados;
import net.weg.gestor.domain.model.Secao;
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
    private UsuarioRepository usuarioRepository;

    public List<ProjectInputConsAlocDTO> saveConsultoresAlocados(ProjectInputDTO project, Long idCadastrado) {
        List<ConsultoresAlocados> consultores = consultoresAlocadosAssembler.toCollectionEntity(project.getConsultores());
        for (int i = 0; i < consultores.size(); ++i) {
            consultores.get(i).setProjetos_id(idCadastrado);
        }

        consultoresAlocadosRepository.saveAll(consultores);
        return null;
    }

    public ConsultorAlocadoDTO alocarConsultor(Long projetoId, Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId) || !projetoRepository.existsById(projetoId)) {
            throw new NegocioException("Verifique os valores de ProjetoId e UsuarioId informados");
        }
        ConsultoresAlocados newConsultor = new ConsultoresAlocados();
        newConsultor.setProjetos_id(projetoId);
        newConsultor.setUsuarios_id(usuarioId);
        consultoresAlocadosRepository.save(newConsultor);
        return consultoresAlocadosAssembler.toModel(newConsultor);

    }

}
