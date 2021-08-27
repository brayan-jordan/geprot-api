package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.ProjetoInteiroDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjetoInteiroInputDTO;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.service.ProjetoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/projetos")
@AllArgsConstructor
public class ProjetoController {

    private ProjetoService projetoService;

    @GetMapping("/listar")
    public ArrayList<ProjetoInteiroDTO> listarTodosProjetos(){
        return projetoService.listartodos();
    }

    @GetMapping("/listarstatus/{typeStatus}")
    public ArrayList<ProjetoInteiroDTO> listarProjetosAndamento(@PathVariable int typeStatus) {
        return projetoService.listarStatus(typeStatus);
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjetoInteiroDTO cadastrarInteiro(@Valid @RequestBody ProjetoInteiroInputDTO projeto) {
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
