package net.weg.gestor.api.map;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.UsuarioLoginInputDTO;
import net.weg.gestor.api.model.input.UsuarioInputDTO;
import net.weg.gestor.domain.entities.Usuario;
import net.weg.gestor.domain.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsuarioAssembler {

    private RoleUsuarioRepository roleUsuarioRepository;
    private ConsultorAlocadoRepository consultorAlocadoRepository;
    private GestorRepository gestorRepository;
    private SecaoAssembler secaoAssembler;
    private SecaoRepository secaoRepository;
    private ModelMapper modelMapper;
    private UsuarioRepository usuarioRepository;

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

    public Usuario toEntityLogin(UsuarioLoginInputDTO usuarioInputDTO) {
        return  modelMapper.map(usuarioInputDTO, Usuario.class);

    }

//    public UsuarioConsultorDTO toModelConsultor(Usuario usuario) {
//        UsuarioConsultorDTO returnUsuario = modelMapper.map(usuario, UsuarioConsultorDTO.class);
//        returnUsuario.setDemandas(consultorAlocadoRepository.findByIdConsultor2(returnUsuario.getId()).size());
//        returnUsuario.setDataFormatada(returnUsuario.getDataCadastro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//        return returnUsuario;
//    }

//    public List<UsuarioConsultorDTO> toCollectionModelConsultor(List<Usuario> usuarios) {
//        return usuarios.stream().map(this::toModelConsultor).collect(Collectors.toList());
//    }

//    public Usuario toEntityLogin(UsuarioLoginInputDTO usuarioLoginInputDTO) {
//        return modelMapper.map(usuarioLoginInputDTO, Usuario.class);
//    }

//    public GestorDTO toModelLogin(Usuario usuario) {
//        GestorDTO gestorDTO = new GestorDTO();
//        gestorDTO.setUsuario(modelMapper.map(usuario, UsuarioDTO.class));
//        Usuario usuario1 = usuarioRepository.findByIdUsuario(gestorDTO.getUsuario().getId());
//        Gestor gestor = gestorRepository.findByUsuarioId(usuario1.getId());
//        gestorDTO.setId(gestor.getId());
//        gestorDTO.setSecao(secaoAssembler.toModel(gestor.getSecoes_id()));
//        return gestorDTO;
//    }

}
