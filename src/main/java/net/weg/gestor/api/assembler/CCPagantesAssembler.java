package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.model.CCPagantes;
import net.weg.gestor.api.model.CCPagantesModel;
import net.weg.gestor.api.model.centrodecustoinput.CCPagantesInput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CCPagantesAssembler {

    private ModelMapper modelMapper;

    public CCPagantes toEntity(CCPagantesInput ccPagantesInput) {
        return modelMapper.map(ccPagantesInput, CCPagantes.class);
    }

    public CCPagantes toEntity(CCPagantesModel ccPagantesModel) {
        return modelMapper.map(ccPagantesModel, CCPagantes.class);
    }

    public CCPagantesModel toModel(CCPagantes ccPagantes) {
        return modelMapper.map(ccPagantes, CCPagantesModel.class);
    }

    public List<CCPagantesModel> toCollectionModel(List<CCPagantes> ccPagantes) {
        return ccPagantes.stream().map(this::toModel).collect(Collectors.toList());
    }
}
