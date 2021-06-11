package net.weg.gestor.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.service.ProjetoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projetos")
@AllArgsConstructor
public class ProjetoController {

    private ProjetoService projetoService;

    @GetMapping("/listar")
    public List<Projeto> listarTodosProjetos(){
        return projetoService.listartodos();
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Projeto cadastraProjeto(@Valid @RequestBody Projeto projeto){
        return projetoService.cadastrar(projeto);
    }

    @PutMapping("/editar/{projetoId}")
    public Projeto editar(@Valid @PathVariable Long projetoId, @RequestBody Projeto projeto){
        return projetoService.editar(projetoId,projeto);
    }

    @PutMapping("/editar/atrasado/{projetoId}")
    public Projeto editarAtrasado(@Valid @PathVariable Long projetoId){
        return projetoService.editarAtrasado(projetoId);
    }

    @PutMapping("/editar/concluido/{projetoId}")
    public Projeto editarConcluido(@Valid @PathVariable Long projetoId){
        return projetoService.editarConcluida(projetoId);
    }

}
