package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.SecaoDTO;
import net.weg.gestor.domain.entities.Secao;
import net.weg.gestor.domain.repository.SecaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class SecaoAssembler {

    private SecaoRepository secaoRepository;
    private ModelMapper modelMapper;

    public SecaoDTO toModel(Secao secao) {
        return modelMapper.map(secao, SecaoDTO.class);
    }

    public List<SecaoDTO> toCollectionModel(List<Secao> secoes) {
        return secoes.stream().map(this::toModel).collect(Collectors.toList());
    }


//    public List<UsuarioDTO> toCollectionModel(List<Usuario> usuarios) {
//        return usuarios.stream().map(this::toModel).collect(Collectors.toList());
//
//    }

}
