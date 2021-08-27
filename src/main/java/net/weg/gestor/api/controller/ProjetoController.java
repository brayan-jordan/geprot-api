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

    @PatchMapping("/editar/atrasado/{projetoId}")
    public void editarAtrasado(@Valid @PathVariable Long projetoId){
        projetoService.editarAtrasado(projetoId);
    }

    @PatchMapping("/editar/concluido/{projetoId}")
    public void editarConcluido(@Valid @PathVariable Long projetoId){
        projetoService.editarConcluida(projetoId);
    }

    @PatchMapping("/editar/andamento/{projetoId}")
    public void editarAndamento(@Valid @PathVariable Long projetoId){
        projetoService.editarAndamento(projetoId);
    }
}
