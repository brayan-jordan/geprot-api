package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.UsuarioAssembler;
import net.weg.gestor.api.model.RoleUsuarioDTO;
import net.weg.gestor.api.model.UsuarioDTO;
import net.weg.gestor.api.model.usuarioinputDTO.UsuarioEditarInputDTO;
import net.weg.gestor.domain.model.Usuario;
import net.weg.gestor.domain.model.RoleUsuarios;
import net.weg.gestor.domain.service.UsuarioService;
import net.weg.gestor.api.model.usuarioinputDTO.UsuarioInputDTO;
import net.weg.gestor.domain.service.RoleUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioService;
    private UsuarioAssembler usuarioAssembler;
    private RoleUsuarioService roleUsuarioService;


    @GetMapping("/buscar/{usuarioId}")
    public UsuarioDTO buscarUmUsuarioPorId(@PathVariable Long usuarioId) {
        return usuarioService.buscar(usuarioId);
    }

    @DeleteMapping("/deletar/{usuarioId}")
    public ResponseEntity<Usuario> remover(@PathVariable Long usuarioId) {
        return usuarioService.excluir(usuarioId);
    }

    @PutMapping("/editar/{usuarioId}")
    public UsuarioDTO editar(@Valid @PathVariable Long usuarioId, @RequestBody UsuarioEditarInputDTO usuario) {
        return usuarioService.editar(usuarioId, usuario);
    }

    @GetMapping("/listar")
    public List<UsuarioDTO> list2() {
        return usuarioService.listartodos();
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO cadastrar(@RequestBody UsuarioInputDTO usuario) {
        Usuario novoUsuario = usuarioAssembler.toEntity(usuario);
        RoleUsuarios novaRole = new RoleUsuarios();
        novaRole.setUsuarios_id(novoUsuario.getId());
        novoUsuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        novoUsuario.getSecao().setId(usuario.getSecao().getId());
        Usuario usuario1 = usuarioService.cadastrar(novoUsuario);
        novaRole.setRole_nome("ROLE_CONSULTOR");
        roleUsuarioService.cadastrar(novaRole);
        return usuarioAssembler.toModel(usuario1);
    }

    @PutMapping("/editar/admin/{usuarioId}")
    public RoleUsuarioDTO editaPermissaoAdmin(@Valid @PathVariable long usuarioId){
        return roleUsuarioService.editarPermissaoAdmin(usuarioId);
    }

    @PutMapping("/editar/user/{usuarioId}")
    public RoleUsuarioDTO editaPermissaoUser(@Valid @PathVariable long usuarioId){
        return roleUsuarioService.editarPermissaoUser(usuarioId);
    }

}