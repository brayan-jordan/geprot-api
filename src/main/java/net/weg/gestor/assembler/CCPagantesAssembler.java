package net.weg.gestor.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.model.CCPagantes;
import net.weg.gestor.model.CCPagantesModel;
import net.weg.gestor.model.centrodecustoinput.CCPagantesInput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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

}
