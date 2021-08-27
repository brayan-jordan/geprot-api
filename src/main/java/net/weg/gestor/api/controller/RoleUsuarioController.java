package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.RoleUsuarioAssembler;
import net.weg.gestor.api.model.RoleUsuarioDTO;
import net.weg.gestor.api.model.usuarioinputDTO.RoleUsuarioInputDTO;
import net.weg.gestor.domain.model.RoleUsuarios;
import net.weg.gestor.domain.repository.RoleUsuarioRepository;
import net.weg.gestor.domain.service.RoleUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleUsuarioController {

    RoleUsuarioAssembler roleUsuarioAssembler;
    RoleUsuarioRepository roleUsuarioRepository;
    RoleUsuarioService roleUsuarioService;

    @GetMapping
    public List<RoleUsuarioDTO> listar(){ return  roleUsuarioService.listar(); }

    @GetMapping("/buscar/{roleId}")
    public ResponseEntity<RoleUsuarioDTO> buscarPorId(@PathVariable Long roleId){
        return roleUsuarioService.buscarId(roleId); }

    @GetMapping("buscarpessoa/{usuario_id}")
    public RoleUsuarioDTO buscarPorIdUsuario(@PathVariable Long usuario_id){
        return roleUsuarioService.buscarPorIdUsuario(usuario_id);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<RoleUsuarioDTO> remover(@PathVariable Long roleId){
        if (!roleUsuarioRepository.existsById(roleId)){
            return ResponseEntity.notFound().build();
        }
        roleUsuarioService.deletar(roleId);
        return ResponseEntity.noContent().build();
    }

}
