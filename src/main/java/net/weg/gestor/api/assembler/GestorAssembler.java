package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.model.Usuario;
import net.weg.gestor.api.model.GestorModel;
import net.weg.gestor.api.model.gestorinput.GestorInput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GestorAssembler {

    private ModelMapper modelMapper;

    public GestorModel toModel(Usuario usuario) {
        return modelMapper.map(usuario, GestorModel.class);

    }

    public List<GestorModel> toCollectionModel(List<Usuario> gestores) {
        return gestores.stream().map(this::toModel).collect(Collectors.toList());

    }

    public Usuario toEntity(GestorModel gestorModel) {
        return modelMapper.map(gestorModel, Usuario.class);

    }

    public Usuario toEntity(GestorInput gestorInput) {
        return  modelMapper.map(gestorInput, Usuario.class);

    }

}
