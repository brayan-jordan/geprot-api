package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.GestorAssembler;
import net.weg.gestor.domain.model.Usuario;
import net.weg.gestor.domain.model.RoleUsuarios;
import net.weg.gestor.domain.service.GestorService;
import net.weg.gestor.api.model.GestorModel;
import net.weg.gestor.api.model.gestorinput.GestorInput;
import net.weg.gestor.domain.service.RoleUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gerentes")
@AllArgsConstructor
public class GestorController {

    private GestorService gestorService;
    private GestorAssembler gestorAssembler;
    private RoleUsuarioService roleUsuarioService;

    @GetMapping("/listartodos")
    public List<Usuario> listarTodosOsGestores() {
        return gestorService.listartodos();

    }

    @GetMapping("/buscar/{gestorId}")
    public ResponseEntity<Usuario> buscarUmGestorPorId(@PathVariable Long gestorId) {
        return gestorService.buscar(gestorId);

    }

//    @PostMapping("/cadastrar")
//    public Gestor cadastrarNovoGestor(@Valid @RequestBody Gestor gestor) {
//        return gestorService.cadastrar(gestor);
//
//    }

    @DeleteMapping("/deletar/{gestorId}")
    public ResponseEntity<Usuario> remover(@PathVariable Long gestorId) {
        return gestorService.excluir(gestorId);

    }

    @PutMapping("/editar/{gestorId}")
    public ResponseEntity<Usuario> editar(@Valid @PathVariable Long gestorid, @RequestBody Usuario usuario) {
        return gestorService.editar(gestorid, usuario);

    }

    @GetMapping("/listar2")
    public List<GestorModel> list2() {
        return gestorService.list2();

    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public GestorModel criar2(@Valid @RequestBody GestorInput gestor) {
        Usuario novoUsuario = gestorAssembler.toEntity(gestor);
        RoleUsuarios novaRole = new RoleUsuarios();
        novaRole.setId(novoUsuario.getUsuario().getId());
        Usuario usuario1 = gestorService.cadastrar(novoUsuario);
        novaRole.setNome_role("ROLE_USER");
        roleUsuarioService.cadastrar(novaRole);
        return gestorAssembler.toModel(novoUsuario);


    }

}
