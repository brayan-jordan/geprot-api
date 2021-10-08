package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.ConsultorNaoAlocadoDTO;
import net.weg.gestor.api.model.ProjetoCardDTO;
import net.weg.gestor.api.model.ProjetoDetalhadoDTO;
import net.weg.gestor.domain.service.ConsultoresAlocadosService;
import net.weg.gestor.domain.service.ProjetoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
@AllArgsConstructor
public class ProjetoController {

    private ProjetoService projetoService;
    private ConsultoresAlocadosService consultoresAlocadosService;

    @GetMapping("/listar/{secaoId}")
    public List<ProjetoCardDTO> listarPorSecao(@PathVariable Long secaoId) {
        return projetoService.listarPorSecao(secaoId);
    }

    @GetMapping("/buscar/{secaoId}/{projetoId}")
    public ProjetoDetalhadoDTO buscarInfoProjeto(@PathVariable Long secaoId, @PathVariable Long projetoId) {
        return projetoService.buscarProjeto(secaoId, projetoId);
    }


//    @GetMapping("/listarcontaining/{secaoId}/{busca}/{typeStatus}")
//    public List<ProjetoDTO> listarContaining(@PathVariable Long secaoId, @PathVariable String busca, @PathVariable int typeStatus) {
//        return projetoService.listarContaining(secaoId, busca, typeStatus);
//    }
//
//    @GetMapping("/listar/{secaoId}")
//    public List<ProjetoDTO> listarTodosProjetosDaSecao(@PathVariable Long secaoId){
//        return projetoService.listartodos(secaoId);
//    }
//
//    @GetMapping("/listar/{secaoId}/{typeStatus}")
//    public List<ProjetoDTO> listarTodosProjetosDaSecaoPorStatus(@PathVariable Long secaoId, @PathVariable int typeStatus){
//        if (typeStatus == 0) {
//            return projetoService.listartodos(secaoId);
//        }
//        return projetoService.listartodosstatus(secaoId, typeStatus);
//    }
//
//    @GetMapping("/listar/projetos/{projetoId}")
//    public ProjetoDTO listarProjetoID(@PathVariable Long projetoId){
//        return projetoService.listarPorId(projetoId);
//    }
//
//    @GetMapping("/listarstatus/{typeStatus}")
//    public List<ProjetoDTO> listarProjetosAndamento(@PathVariable int typeStatus) {
//        return projetoService.listarStatus(typeStatus);
//    }
//
//    @PostMapping("/cadastrar")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String cadastrar(@Valid @RequestBody ProjetoInputDTO projeto) {
//        return projetoService.cadastrar(projeto);
//    }

//    @PatchMapping("/editar/atrasado/{projetoId}")
//    public void editarAtrasado(@Valid @PathVariable Long projetoId){
//        projetoService.editarAtrasado(projetoId);
//    }

//    @PatchMapping("/editar/concluido/{projetoId}")
//    public void editarConcluido(@Valid @PathVariable Long projetoId){
//        projetoService.editarConcluida(projetoId);
//    }
//
//    @PatchMapping("/editar/andamento/{projetoId}")
//    public void editarAndamento(@Valid @PathVariable Long projetoId){
//        projetoService.editarAndamento(projetoId);
//    }
//
//    @PatchMapping("/iniciar/{projetoId}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void iniciarProjeto(@PathVariable Long projetoId) {
//        projetoService.iniciarProjeto(projetoId);
//    }

//    @GetMapping("/naoalocados/{usuarioId}")
//    public List<ProjetoDTO> buscarNaoAlocados(@PathVariable Long usuarioId) {
//        return projetoService.findNoAllocateds(usuarioId);
//    }



}
