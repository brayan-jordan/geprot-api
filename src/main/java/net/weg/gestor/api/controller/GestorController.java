package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.GestorAssembler;
import net.weg.gestor.api.model.GestorDTO;
import net.weg.gestor.api.model.input.GestorInputUpdate;
import net.weg.gestor.domain.entities.Gestor;
import net.weg.gestor.domain.entities.Usuario;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.GestorRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import net.weg.gestor.domain.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gestor")
@AllArgsConstructor
public class GestorController {

    private GestorRepository gestorRepository;
    private UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;
    private GestorAssembler gestorAssembler;

    @PutMapping("/editar/{gestorId}")
    public GestorDTO editar(@PathVariable long gestorId, @RequestBody GestorInputUpdate gestorInputUpdate){
        Gestor gestor = gestorRepository.findById(gestorId).orElseThrow(() -> new NegocioException("Não existe um gestor com esse id"));
        Usuario usuario = usuarioRepository.findById(gestor.getUsuario().getId()).orElseThrow(() -> new NegocioException("Não existe um usuario com esse id"));
        gestor.setUsuario(usuarioService.atualizarUsuario(usuario, gestorInputUpdate));
        return gestorAssembler.toModel(gestor);
    }

}
