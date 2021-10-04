package net.weg.gestor.api.assembler;


import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.RoleUsuarioDTO;
import net.weg.gestor.api.model.usuarioinputDTO.RoleUsuarioInputDTO;
import net.weg.gestor.domain.entities.RoleUsuarios;
import net.weg.gestor.domain.repository.RoleUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class RoleUsuarioAssembler {

    private ModelMapper modelMapper;
    private RoleUsuarioRepository roleUsuarioRepository;

    public RoleUsuarios toEntity(RoleUsuarioInputDTO roleUsuarioInputDTO){
        return modelMapper.map(roleUsuarioInputDTO, RoleUsuarios.class);
    }

    public List<RoleUsuarioDTO> toCollectionModel(List<RoleUsuarios> roleUsuarios){
        return  roleUsuarios.stream().map(this::toModel).collect(Collectors.toList());
    }

    public RoleUsuarioDTO toModel(RoleUsuarios roleUsuarios){
        return modelMapper.map(roleUsuarios, RoleUsuarioDTO.class);
    }

}
