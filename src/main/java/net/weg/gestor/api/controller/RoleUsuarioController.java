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

    @PostMapping
    public RoleUsuarioDTO cadastrar(@Valid @RequestBody RoleUsuarioInputDTO roleUsuarioInputDTO){
        RoleUsuarios novaRole = roleUsuarioAssembler.toEntity(roleUsuarioInputDTO);
        RoleUsuarios roleUsuarios = roleUsuarioService.cadastrar(novaRole);
        return roleUsuarioAssembler.toModel(roleUsuarios);
    }

    @GetMapping
    public List<RoleUsuarioDTO> listar(){ return  roleUsuarioService.listar(); }

    @GetMapping("/buscar/{roleId}")
    public ResponseEntity<RoleUsuarioDTO> buscarPorId(@PathVariable Long roleId){ return roleUsuarioService.buscarId(roleId); }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleUsuarioDTO> editar(@Valid @PathVariable Long roleId, @RequestBody RoleUsuarioInputDTO roleUsuarioInputDTO){
        RoleUsuarios roleUsuarios1 = roleUsuarioAssembler.toEntity(roleUsuarioInputDTO);
        roleUsuarioService.editar(roleId, roleUsuarios1);
        return ResponseEntity.ok(roleUsuarioAssembler.toModel(roleUsuarios1));
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
