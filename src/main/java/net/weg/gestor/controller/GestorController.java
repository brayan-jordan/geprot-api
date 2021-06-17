package net.weg.gestor.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.assembler.GestorAssembler;
import net.weg.gestor.domain.model.Gestor;
import net.weg.gestor.domain.model.Secao;
import net.weg.gestor.domain.service.GestorService;
import net.weg.gestor.domain.service.SecaoService;
import net.weg.gestor.model.GestorModel;
import net.weg.gestor.model.gestorinput.GestorInput;
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

    @GetMapping("/listartodos")
    public List<Gestor> listarTodosOsGestores() {
        return gestorService.listartodos();

    }

    @GetMapping("/buscar/{gestorId}")
    public ResponseEntity<Gestor> buscarUmGestorPorId(@PathVariable Long gestorId) {
        return gestorService.buscar(gestorId);

    }

    @PostMapping("/cadastrar")
    public Gestor cadastrarNovoGestor(@Valid @RequestBody Gestor gestor) {
        return gestorService.cadastrar(gestor);

    }

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

    @PostMapping("/cadastrar2")
    @ResponseStatus(HttpStatus.CREATED)
    public GestorModel criar2(@Valid @RequestBody GestorInput gestor) {
        Gestor novoGestor = gestorAssembler.toEntity(gestor);
        Gestor gestor1 = gestorService.cadastrar(novoGestor);

        return gestorAssembler.toModel(gestor1);


    }

}
