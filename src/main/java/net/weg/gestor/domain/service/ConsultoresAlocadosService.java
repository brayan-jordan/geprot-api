package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.ConsultoresAlocadosAssembler;
import net.weg.gestor.api.model.projetoinputDTO.ProjectInputConsAlocDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjectInputDTO;
import net.weg.gestor.domain.model.ConsultoresAlocados;
import net.weg.gestor.domain.repository.ConsultoresAlocadosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ConsultoresAlocadosService {

    private ConsultoresAlocadosRepository consultoresAlocadosRepository;
    private ConsultoresAlocadosAssembler consultoresAlocadosAssembler;

    public List<ProjectInputConsAlocDTO> saveConsultoresAlocados(ProjectInputDTO project, Long idCadastrado) {
        List<ConsultoresAlocados> consultores = consultoresAlocadosAssembler.toCollectionEntity(project.getConsultores());
        for (int i = 0; i < consultores.size(); ++i) {
            consultores.get(i).setProjetos_id(idCadastrado);
        }

        consultoresAlocadosRepository.saveAll(consultores);
        return null;
    }

}
