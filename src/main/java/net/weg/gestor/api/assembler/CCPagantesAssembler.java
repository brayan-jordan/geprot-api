package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.model.CCPagantes;
import net.weg.gestor.api.model.CCPagantesDTO;
import net.weg.gestor.api.model.centrodecustoinputDTO.CCPagantesInputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CCPagantesAssembler {

    private ModelMapper modelMapper;

    public CCPagantes toEntity(CCPagantesInputDTO ccPagantesInputDTO) {
        return modelMapper.map(ccPagantesInputDTO, CCPagantes.class);
    }

    public CCPagantes toEntity(CCPagantesDTO ccPagantesDTO) {
        return modelMapper.map(ccPagantesDTO, CCPagantes.class);
    }

    public CCPagantes toEntityInput(CCPagantesInputDTO ccPagantesDTO) {
        return modelMapper.map(ccPagantesDTO, CCPagantes.class);
    }

    public CCPagantesDTO toModel(CCPagantes ccPagantes) {
        return modelMapper.map(ccPagantes, CCPagantesDTO.class);
    }

    public List<CCPagantesDTO> toCollectionModel(List<CCPagantes> ccPagantes) {
        return ccPagantes.stream().map(this::toModel).collect(Collectors.toList());
    }

    public List<CCPagantes> toCollectionEntity(List<CCPagantesInputDTO> ccPagantes) {
        return ccPagantes.stream().map(this::toEntityInput).collect(Collectors.toList());
    }
}
