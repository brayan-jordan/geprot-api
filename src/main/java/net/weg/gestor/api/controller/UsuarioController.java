package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.UsuarioAssembler;
import net.weg.gestor.api.model.UsuarioDTO;
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

    @GetMapping("/listartodos")
    public List<Usuario> listarTodosOsGestores() {
        return usuarioService.listartodos();

    }

    @GetMapping("/buscar/{usuarioId}")
    public ResponseEntity<Usuario> buscarUmGestorPorId(@PathVariable Long usuarioId) {
        return usuarioService.buscar(usuarioId);

    }

    @DeleteMapping("/deletar/{usuarioId}")
    public ResponseEntity<Usuario> remover(@PathVariable Long usuarioId) {
        return usuarioService.excluir(usuarioId);

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
        novaRole.setId_usuarios(novoUsuario.getId());
        novoUsuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        novoUsuario.getSecao().setId(usuario.getSecao().getId());
        Usuario usuario1 = usuarioService.cadastrar(novoUsuario);
        novaRole.setNome_role("ROLE_USER");
        roleUsuarioService.cadastrar(novaRole);
        return usuarioAssembler.toModel(usuario1);


    }

}
