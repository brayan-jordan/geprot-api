package net.weg.gestor.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.model.Gestor;
import net.weg.gestor.model.GestorModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GestorAssembler {

    private ModelMapper modelMapper;

    public GestorModel toModel(Gestor gestor) {
        return modelMapper.map(gestor, GestorModel.class);

    }

    public List<GestorModel> toCollectionModel(List<Gestor> gestores) {
        return gestores.stream().map(this::toModel).collect(Collectors.toList());

    }

    public Gestor toEntity(GestorModel gestorModel) {
        return modelMapper.map(gestorModel, Gestor.class);

    }

}
