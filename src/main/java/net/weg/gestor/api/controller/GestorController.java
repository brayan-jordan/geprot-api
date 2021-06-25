package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.GestorAssembler;
import net.weg.gestor.domain.model.Gestor;
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
    public List<Gestor> listarTodosOsGestores() {
        return gestorService.listartodos();

    }

    @GetMapping("/buscar/{gestorId}")
    public ResponseEntity<Gestor> buscarUmGestorPorId(@PathVariable Long gestorId) {
        return gestorService.buscar(gestorId);

    }

//    @PostMapping("/cadastrar")
//    public Gestor cadastrarNovoGestor(@Valid @RequestBody Gestor gestor) {
//        return gestorService.cadastrar(gestor);
//
//    }

    @DeleteMapping("/deletar/{gestorId}")
    public ResponseEntity<Gestor> remover(@PathVariable Long gestorId) {
        return gestorService.excluir(gestorId);

    }

    @PutMapping("/editar/{gestorId}")
    public ResponseEntity<Gestor> editar(@Valid @PathVariable Long gestorid, @RequestBody Gestor gestor) {
        return gestorService.editar(gestorid, gestor);

    }

    @GetMapping("/listar2")
    public List<GestorModel> list2() {
        return gestorService.list2();

    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public GestorModel criar2(@Valid @RequestBody GestorInput gestor) {
        Gestor novoGestor = gestorAssembler.toEntity(gestor);
        RoleUsuarios novaRole = new RoleUsuarios();
        novaRole.setId(novoGestor.getUsuario().getId());
        Gestor gestor1 = gestorService.cadastrar(novoGestor);
        novaRole.setNome_role("ROLE_USER");
        roleUsuarioService.cadastrar(novaRole);
        return gestorAssembler.toModel(novoGestor);


    }

}
