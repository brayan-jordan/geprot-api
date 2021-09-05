package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.ReturnUsuarioDTO;
import net.weg.gestor.api.model.UsuarioLoginInputDTO;
import net.weg.gestor.api.model.UsuarioDTO;
import net.weg.gestor.api.model.usuarioinputDTO.UsuarioInputDTO;
import net.weg.gestor.domain.model.Usuario;
import net.weg.gestor.domain.repository.RoleUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UsuarioAssembler {

    private RoleUsuarioRepository roleUsuarioRepository;
    private ModelMapper modelMapper;

    public UsuarioDTO toModel(Usuario usuario) {
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        usuarioDTO.setPermissao(roleUsuarioRepository.findRoleByIdUsuario(usuario.getId()).getRole_nome());
        return usuarioDTO;

    }

    public List<UsuarioDTO> toCollectionModel(List<Usuario> usuarios) {
        return usuarios.stream().map(this::toModel).collect(Collectors.toList());

    }

//    public Usuario toEntity(UsuarioDTO usuarioDTO) {
//        return modelMapper.map(usuarioDTO, Usuario.class);
//
//    }

    public Usuario toEntity(UsuarioInputDTO usuarioInputDTO) {
        return  modelMapper.map(usuarioInputDTO, Usuario.class);

    }

    public Usuario toEntityLogin(UsuarioLoginInputDTO usuarioLoginInputDTO) {
        return modelMapper.map(usuarioLoginInputDTO, Usuario.class);
    }

    public ReturnUsuarioDTO toModelLogin(Usuario usuario) {
        return modelMapper.map(usuario, ReturnUsuarioDTO.class);
    }

}
