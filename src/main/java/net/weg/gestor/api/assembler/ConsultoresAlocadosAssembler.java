package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.weg.gestor.api.model.AlocarConsultorInputDTO;
import net.weg.gestor.api.model.ConsultorAlocadoDTO;
import net.weg.gestor.api.model.ConsultorDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjectInputConsAlocDTO;
import net.weg.gestor.domain.model.ConsultoresAlocados;
import net.weg.gestor.domain.repository.ConsultoresAlocadosRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ConsultoresAlocadosAssembler {

    private ModelMapper modelMapper;
    private UsuarioRepository usuarioRepository;

    public ConsultoresAlocados toEntity(ProjectInputConsAlocDTO consultor) {
        return modelMapper.map(consultor, ConsultoresAlocados.class);
    }

    public List<ConsultoresAlocados> toCollectionEntity(List<ProjectInputConsAlocDTO> consultores) {
        return consultores.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public ConsultorDTO toModel(ConsultoresAlocados consultoresAlocados) {
       ConsultorDTO consultorDTO = modelMapper.map(consultoresAlocados, ConsultorDTO.class);
       consultorDTO.setNome(usuarioRepository.findByIdUsuario(consultoresAlocados.getUsuarios_id()).getNome());
        return consultorDTO;
    }

    public ConsultoresAlocados toEntity(AlocarConsultorInputDTO alocarConsultorInputDTO) {
        return modelMapper.map(alocarConsultorInputDTO, ConsultoresAlocados.class);
    }

}
