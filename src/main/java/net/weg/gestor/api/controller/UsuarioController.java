package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.UsuarioAssembler;
import net.weg.gestor.api.model.UsuarioDTO;
import net.weg.gestor.domain.model.Usuario;
import net.weg.gestor.domain.model.RoleUsuarios;
import net.weg.gestor.domain.service.UsuarioService;
import net.weg.gestor.api.model.GestorModel;
import net.weg.gestor.api.model.usuarioinputDTO.UsuarioInputDTO;
import net.weg.gestor.domain.service.RoleUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gerentes")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioService;
    private UsuarioAssembler usuarioAssembler;
    private RoleUsuarioService roleUsuarioService;

    @GetMapping("/listartodos")
    public List<Usuario> listarTodosOsGestores() {
        return usuarioService.listartodos();

    }

    @GetMapping("/buscar/{gestorId}")
    public ResponseEntity<Usuario> buscarUmGestorPorId(@PathVariable Long gestorId) {
        return usuarioService.buscar(gestorId);

    }

    @DeleteMapping("/deletar/{gestorId}")
    public ResponseEntity<Usuario> remover(@PathVariable Long gestorId) {
        return usuarioService.excluir(gestorId);

    }

    @PutMapping("/editar/{usuarioId}")
    public ResponseEntity<Usuario> editar(@Valid @PathVariable Long usuarioid, @RequestBody Usuario usuario) {
        return usuarioService.editar(usuarioid, usuario);

    }

    @GetMapping("/listar2")
    public List<UsuarioDTO> list2() {
        return usuarioService.list2();

    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO criar2(@Valid @RequestBody UsuarioInputDTO usuario) {
        Usuario novoUsuario = usuarioAssembler.toEntity(usuario);
        RoleUsuarios novaRole = new RoleUsuarios();
        novaRole.setId(novoUsuario.getId());
        Usuario usuario1 = usuarioService.cadastrar(novoUsuario);
        novaRole.setNome_role("ROLE_USER");
        roleUsuarioService.cadastrar(novaRole);
        return usuarioAssembler.toModel(usuario1);


    }

}
