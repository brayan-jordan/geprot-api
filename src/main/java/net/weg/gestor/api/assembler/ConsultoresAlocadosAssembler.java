package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.weg.gestor.api.model.projetoinputDTO.ProjectInputConsAlocDTO;
import net.weg.gestor.domain.model.ConsultoresAlocados;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ConsultoresAlocadosAssembler {

    private ModelMapper modelMapper;

    public ConsultoresAlocados toEntity(ProjectInputConsAlocDTO consultor) {
        return modelMapper.map(consultor, ConsultoresAlocados.class);
    }

    public List<ConsultoresAlocados> toCollectionEntity(List<ProjectInputConsAlocDTO> consultores) {
        return consultores.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
