package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.GestorDTO;
import net.weg.gestor.domain.entities.Gestor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GestorAssembler {

    private ModelMapper modelMapper;

    public List<GestorDTO> toCollectionModel(List<Gestor> gestores){
        return gestores.stream().map(this::toModel).collect(Collectors.toList());
    }

    public GestorDTO toModel(Gestor gestor){
        return modelMapper.map(gestor, GestorDTO.class);
    }
}
