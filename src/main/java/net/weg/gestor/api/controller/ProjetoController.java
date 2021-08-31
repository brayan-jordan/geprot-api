package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.ProjetoInteiroDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjectInputDTO;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.service.ProjetoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projetos")
@AllArgsConstructor
public class ProjetoController {

    private ProjetoService projetoService;
    private ProjetoRepository projetoRepository;

//    @GetMapping("/listar")
//    public ArrayList<ProjetoInteiroDTO> listarTodosProjetos(){
//        return projetoService.listartodos();
//    }
//
//    @GetMapping("/listarstatus/{typeStatus}")
//    public ArrayList<ProjetoInteiroDTO> listarProjetosAndamento(@PathVariable int typeStatus) {
//        return projetoService.listarStatus(typeStatus);
//    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastrarInteiro(@Valid @RequestBody ProjectInputDTO projeto) {
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

    @PatchMapping("/iniciar/{projetoId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void iniciarProjeto(@PathVariable Long projetoId) {
        projetoService.iniciarProjeto(projetoId);
    }

    @GetMapping("/teste")
    public List<Projeto> tste() {
        return projetoRepository.findAll();
    }
}
