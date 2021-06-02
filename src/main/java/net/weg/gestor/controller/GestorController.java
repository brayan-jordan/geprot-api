package net.weg.gestor.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.model.Gestor;
import net.weg.gestor.domain.repository.GestorRepository;
import net.weg.gestor.domain.service.GestorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gerentes")
@AllArgsConstructor
public class GestorController {

    private GestorRepository gestorRepository;
    private GestorService gestorService;

    @GetMapping("/listartodos")
    public List<Gestor> listarTodosOsGestores() {
        return gestorRepository.findAll();

    }

    @GetMapping("/buscar/{gestorId}")
    public ResponseEntity<Gestor> buscarUmGestorPorId(@PathVariable Long gestorId) {
        return gestorRepository.findById(gestorId).map(gestor -> ResponseEntity.ok(gestor))
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping("/cadastrar")
    public Gestor cadastrarNovoGestor(@Valid @RequestBody Gestor gestor) {
        return gestorService.cadastrar(gestor);

    }

    @DeleteMapping("/deletar/{gestorId}")
    public ResponseEntity<Gestor> remover(@PathVariable Long gestorId) {
        if(!gestorRepository.existsById(gestorId)) {
            return ResponseEntity.notFound().build();

        }

        gestorRepository.deleteById(gestorId);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/editar/{gestorId}")
    public ResponseEntity<Gestor> editar(@Valid @PathVariable Long gestorId, @RequestBody Gestor gestor) {
        if(!gestorRepository.existsById(gestorId)) {
            return ResponseEntity.notFound().build();

        }

        gestor.setIdGestor(gestorId);
        gestor = gestorRepository.save(gestor);
        return ResponseEntity.ok(gestor);

    }

    //teste

}
