package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.GestorAssembler;
import net.weg.gestor.api.model.GestorDTO;
import net.weg.gestor.domain.entities.Gestor;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.GestorRepository;
import net.weg.gestor.domain.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/gestor")
@AllArgsConstructor
public class GestorController {
    /*  Nessa classe terá as rotas dos gestores de edição de senha e nome  */

    private GestorRepository gestorRepository;
    private UsuarioService usuarioService;
    private GestorAssembler gestorAssembler;

    @GetMapping("buscar/{gestorId}")
    public GestorDTO buscarGestor(@PathVariable long gestorId){
        return gestorAssembler.toModel(gestorRepository.findById(gestorId).orElseThrow(() -> new NegocioException("Não existe um gestor com esse id")));
    }

    @PutMapping("editar/senha/{gestorId}/{gestorSenha}")
    public String editarSenha(@PathVariable long gestorId,@PathVariable String gestorSenha){
        Gestor gestor = gestorRepository.findById(gestorId).orElseThrow(() -> new NegocioException("Não existe um gestor com esse id"));
        gestor.setUsuario(usuarioService.atualizarSenha(gestor.getUsuario(),gestorSenha));
        return gestorSenha;
    }

    @PutMapping("editar/nome/{gestorId}/{gestorNome}")
    public GestorDTO editarNome(@PathVariable long gestorId, @PathVariable String gestorNome){
        Gestor gestor = gestorRepository.findById(gestorId).orElseThrow(() -> new NegocioException("Não existe um gestor com esse id"));
        gestor.getUsuario().setNome(gestorNome);
        return gestorAssembler.toModel(gestorRepository.save(gestor));
    }

}
