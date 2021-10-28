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
    /*  Nessa classe terá as rotas dos gestores de edição de senha e nome  */

    private GestorRepository gestorRepository;
    private UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;
    private GestorAssembler gestorAssembler;

    @PutMapping("editar/senha/{gestorId}")
    public String editarSenha(@PathVariable long gestorId, String newSenha){
        Gestor gestor = gestorRepository.findById(gestorId).orElseThrow(() -> new NegocioException("Não existe um gestor com esse id"));
        gestor.setUsuario(usuarioService.atualizarSenha(gestor.getUsuario(),newSenha));
        return newSenha;
    }

    @PutMapping("editar/nome/{gestorId}")
    public String editarNome(@PathVariable long gestorId, String newNome){
        Gestor gestor = gestorRepository.findById(gestorId).orElseThrow(() -> new NegocioException("Não existe um gestor com esse id"));
        gestor.setUsuario(usuarioService.atualizarNome(gestor.getUsuario(),newNome));
        return newNome;
    }

}
