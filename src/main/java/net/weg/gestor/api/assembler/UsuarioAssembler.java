package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.ReturnUsuarioDTO;
import net.weg.gestor.api.model.UsuarioConsultorDTO;
import net.weg.gestor.api.model.UsuarioLoginInputDTO;
import net.weg.gestor.api.model.UsuarioDTO;
import net.weg.gestor.api.model.usuarioinputDTO.UsuarioInputDTO;
import net.weg.gestor.domain.model.ConsultoresAlocados;
import net.weg.gestor.domain.model.Usuario;
import net.weg.gestor.domain.repository.ConsultoresAlocadosRepository;
import net.weg.gestor.domain.repository.RoleUsuarioRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UsuarioAssembler {

    private RoleUsuarioRepository roleUsuarioRepository;
    private ConsultoresAlocadosRepository consultoresAlocadosRepository;
    private ModelMapper modelMapper;

//    public UsuarioDTO toModel(Usuario usuario) {
//        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
//        usuarioDTO.setPermissao(roleUsuarioRepository.findRoleByIdUsuario(usuario.getId()).getRole_nome());
//        return usuarioDTO;
//
//    }

//    public List<UsuarioDTO> toCollectionModel(List<Usuario> usuarios) {
//        return usuarios.stream().map(this::toModel).collect(Collectors.toList());
//
//    }

//    public Usuario toEntity(UsuarioDTO usuarioDTO) {
//        return modelMapper.map(usuarioDTO, Usuario.class);
//
//    }

    public Usuario toEntity(UsuarioInputDTO usuarioInputDTO) {
        return  modelMapper.map(usuarioInputDTO, Usuario.class);

    }

//    public UsuarioConsultorDTO toModelConsultor(Usuario usuario) {
//        UsuarioConsultorDTO returnUsuario = modelMapper.map(usuario, UsuarioConsultorDTO.class);
//        returnUsuario.setDemandas(consultoresAlocadosRepository.findByIdConsultor2(returnUsuario.getId()).size());
//        returnUsuario.setDataFormatada(returnUsuario.getDataCadastro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//        return returnUsuario;
//    }

//    public List<UsuarioConsultorDTO> toCollectionModelConsultor(List<Usuario> usuarios) {
//        return usuarios.stream().map(this::toModelConsultor).collect(Collectors.toList());
//    }

    public Usuario toEntityLogin(UsuarioLoginInputDTO usuarioLoginInputDTO) {
        return modelMapper.map(usuarioLoginInputDTO, Usuario.class);
    }

    public ReturnUsuarioDTO toModelLogin(Usuario usuario) {
        return modelMapper.map(usuario, ReturnUsuarioDTO.class);
    }

}
