package net.weg.gestor.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.model.StatusProjeto;
import net.weg.gestor.domain.service.ProjetoService;
import net.weg.gestor.model.ProjetoModel;
import net.weg.gestor.model.projetoinput.ProjetoInput;
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

    @GetMapping("/listar/{statusprojeto}")
    public List<Projeto> listarProjetosAndamento(@PathVariable String statusprojeto) {

        if (statusprojeto.equals("EM_ANDAMENTO")) {
            return projetoService.listarStatus(StatusProjeto.EM_ANDAMENTO);
        }

        if (statusprojeto.equals("CONCLUIDO")) {
            return projetoService.listarStatus(StatusProjeto.CONCLUIDO);
        }

        if (statusprojeto.equals("ATRASADO")) {
            return projetoService.listarStatus(StatusProjeto.ATRASADO);
        }

        throw new NegocioException("Status indefinido");
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjetoModel cadastraProjeto(@Valid @RequestBody ProjetoInput projeto){
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

    @PutMapping("/editar/andamento/{projetoId}")
    public Projeto editarAndamento(@Valid @PathVariable Long projetoId){
        return projetoService.editarAndamento(projetoId);
    }
}
