package net.weg.gestor.api.map;


import lombok.AllArgsConstructor;
import net.weg.gestor.domain.repository.RoleUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RoleUsuarioAssembler {

    private ModelMapper modelMapper;
    private RoleUsuarioRepository roleUsuarioRepository;

    /*public RoleUsuarios toEntity(RoleUsuarioInputDTO roleUsuarioInputDTO){
        return modelMapper.map(roleUsuarioInputDTO, RoleUsuarios.class);
    }

    public List<RoleUsuarioDTO> toCollectionModel(List<RoleUsuarios> roleUsuarios){
        return  roleUsuarios.stream().map(this::toModel).collect(Collectors.toList());
    }

    public RoleUsuarioDTO toModel(RoleUsuarios roleUsuarios){
        return modelMapper.map(roleUsuarios, RoleUsuarioDTO.class);
    }*/

}
