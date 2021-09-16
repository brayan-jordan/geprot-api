package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.ProjetoDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjetoInputDTO;
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

    @GetMapping("/listar/{secaoId}")
    public List<ProjetoDTO> listarTodosProjetosDaSecao(@PathVariable Long secaoId){
        return projetoService.listartodos(secaoId);
    }

    @GetMapping("/listar/{secaoId}/{typeStatus}")
    public List<ProjetoDTO> listarTodosProjetosDaSecaoPorStatus(@PathVariable Long secaoId, @PathVariable int typeStatus){
        if (typeStatus == 0) {
            return projetoService.listartodos(secaoId);
        }
        return projetoService.listartodosstatus(secaoId, typeStatus);
    }

    @GetMapping("/listar/projetos/{projetoId}")
    public ProjetoDTO listarProjetoID(@PathVariable Long projetoId){
        return projetoService.listarPorId(projetoId);
    }

    @GetMapping("/listarstatus/{typeStatus}")
    public List<ProjetoDTO> listarProjetosAndamento(@PathVariable int typeStatus) {
        return projetoService.listarStatus(typeStatus);
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastrar(@Valid @RequestBody ProjetoInputDTO projeto) {
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

    @GetMapping("/naoalocados/{usuarioId}")
    public List<ProjetoDTO> buscarNaoAlocados(@PathVariable Long usuarioId) {
        return projetoService.findNoAllocateds(usuarioId);
    }

}
