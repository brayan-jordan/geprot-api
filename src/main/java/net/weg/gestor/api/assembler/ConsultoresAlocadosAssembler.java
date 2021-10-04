package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class ConsultoresAlocadosAssembler {

    private ModelMapper modelMapper;
    private UsuarioRepository usuarioRepository;

    /*public ConsultoresAlocados toEntity(AlocarConsultoresInputDTO consultor) {
        return modelMapper.map(consultor, ConsultoresAlocados.class);
    }

    public List<ConsultoresAlocados> toCollectionEntity(List<AlocarConsultoresInputDTO> consultores) {
        return consultores.stream().map(this::toEntity).collect(Collectors.toList());
    }*/

//    public ConsultorDTO toModel(ConsultoresAlocados consultoresAlocados) {
//       ConsultorDTO consultorDTO = modelMapper.map(consultoresAlocados, ConsultorDTO.class);
//       consultorDTO.setNome(usuarioRepository.findByIdUsuario(consultoresAlocados.getConsultor().getId()).getNome());
//        return consultorDTO;
//    }


//    public ConsultoresAlocados toEntity(AlocarConsultorInputDTO alocarConsultorInputDTO) {
//        return modelMapper.map(alocarConsultorInputDTO, ConsultoresAlocados.class);
//    }
}
