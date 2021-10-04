package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.RoleUsuarioAssembler;
import net.weg.gestor.api.model.RoleUsuarioDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.entities.RoleUsuarios;
import net.weg.gestor.domain.repository.RoleUsuarioRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class RoleUsuarioService {

    private RoleUsuarioRepository roleUsuarioRepository;
    private RoleUsuarioAssembler roleUsuarioAssembler;
    private UsuarioRepository usuarioRepository;

    @Transactional
    public RoleUsuarios cadastrar(RoleUsuarios roleUsuarios){
        return roleUsuarioRepository.save(roleUsuarios);
    }

    public List<RoleUsuarioDTO> listar(){
        return roleUsuarioAssembler.toCollectionModel(roleUsuarioRepository.findAll());
    }

    public RoleUsuarios buscar(Long roleId){
        return roleUsuarioRepository.findById(roleId).orElseThrow(() -> new NegocioException("Role não encontrada."));
    }

    public ResponseEntity<RoleUsuarioDTO> buscarId(Long roleId){
        return  roleUsuarioRepository.findById(roleId).map(roleUsuarios -> ResponseEntity.ok(roleUsuarioAssembler.toModel(roleUsuarios))
        )
        .orElseThrow(() -> new NegocioException("Role não encontrada."));
    }

//    public RoleUsuarioDTO buscarPorIdUsuario(Long usuariosId){
//        if (roleUsuarioRepository.findRoleByIdUsuario(usuariosId) == null){
//            throw new NegocioException("Não existe um usuario com esse ID");
//        }
//        return roleUsuarioAssembler.toModel(roleUsuarioRepository.findRoleByIdUsuario(usuariosId));
//    }

//    public RoleUsuarioDTO editarPermissaoAdmin(Long usuarioId){
//        if (!usuarioRepository.existsById(usuarioId)){
//            throw new NegocioException("Não existe um usuario com esse id");
//        }
//        RoleUsuarios roleUsuarios = roleUsuarioRepository.findRoleByIdUsuario(usuarioId);
//        roleUsuarios.setRole_nome("ROLE_GESTOR");
//        roleUsuarios = roleUsuarioRepository.save(roleUsuarios);
//        return roleUsuarioAssembler.toModel(roleUsuarios);
//    }
//
//    public RoleUsuarioDTO editarPermissaoUser(Long usuarioId){
//        if (!usuarioRepository.existsById(usuarioId)){
//            throw new NegocioException("Não existe um usuario com esse id");
//        }
//        RoleUsuarios roleUsuarios = roleUsuarioRepository.findRoleByIdUsuario(usuarioId);
//        roleUsuarios.setRole_nome("ROLE_USER");
//        roleUsuarios = roleUsuarioRepository.save(roleUsuarios);
//        return roleUsuarioAssembler.toModel(roleUsuarios);
//    }

    @Transactional
    public void deletar(Long roleId){
        roleUsuarioRepository.deleteById(roleId);
    }

//    @Transactional
//    public void deletarPorIdUsuario(Long usuarioId){
//        RoleUsuarios roleUsuarios = roleUsuarioRepository.findRoleByIdUsuario(usuarioId);
//        roleUsuarioRepository.deleteById(roleUsuarios.getId());
//    }
}
