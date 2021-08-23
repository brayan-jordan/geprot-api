package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.RoleUsuarioAssembler;
import net.weg.gestor.api.model.RoleUsuarioDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.RoleUsuarios;
import net.weg.gestor.domain.repository.RoleUsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class RoleUsuarioService {

    private RoleUsuarioRepository roleUsuarioRepository;
    private RoleUsuarioAssembler roleUsuarioAssembler;

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

    public ResponseEntity<RoleUsuarioDTO> buscarPorIdUsuario(Long usuariosId){
        return  roleUsuarioRepository.findRoleById(usuariosId).map(roleUsuarios -> ResponseEntity.ok(roleUsuarioAssembler.toModel(roleUsuarios))
        )
                .orElseThrow(() -> new NegocioException("Pessoa não encontrada."));
    }

    public ResponseEntity<RoleUsuarioDTO> editar(Long roleId, RoleUsuarios roleUsuarios){
        if (!roleUsuarioRepository.existsById(roleId)){
            throw new NegocioException("Role inexistente.");
        }

        RoleUsuarios roleUsuarios1 = this.buscar(roleId);
        roleUsuarios.setId(roleId);
        roleUsuarios = roleUsuarioRepository.save(roleUsuarios);
        return ResponseEntity.ok(roleUsuarioAssembler.toModel(roleUsuarios));
    }

    @Transactional
    public void deletar(Long roleId){ roleUsuarioRepository.deleteById(roleId);}
}
