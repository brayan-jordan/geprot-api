package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.projeto.ProjetoAlocarDTO;
import net.weg.gestor.api.model.projeto.ProjetoCardDTO;
import net.weg.gestor.api.model.cadastrarprojetoinput.ProjetoInputDTO;
import net.weg.gestor.domain.service.ProjetoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
@AllArgsConstructor
public class ProjetoController {

    private ProjetoService projetoService;

    @GetMapping("/listar/{secaoId}")
    public List<ProjetoCardDTO> listarPorSecao(@PathVariable Long secaoId) {
        return projetoService.listarPorSecao(secaoId);
    }

    @GetMapping("/buscar/nome/{secaoId}/{nome}")
    public List<ProjetoCardDTO> buscarPorNome(@PathVariable Long secaoId, @PathVariable String nome) {
        return projetoService.buscarPorNome(secaoId, nome);
    }

    @GetMapping("/buscar/responsavel/{secaoId}/{nomeresponsavel}")
    public List<ProjetoCardDTO> buscarPorNomeResponsavel(
            @PathVariable Long secaoId,
            @PathVariable String nomeresponsavel
    ) {
        return projetoService.buscarPorNomeResponsavel(secaoId, nomeresponsavel);
    }

    @GetMapping("/buscar/id/{secaoId}/{id}")
    public List<ProjetoCardDTO> buscarPorId(
            @PathVariable Long secaoId,
            @PathVariable Long id
    ) {
        return projetoService.buscarPorId(secaoId, id);
    }

    @GetMapping("/buscar/nomeestatus/{secaoId}/{campoBusca}/{status}")
    public List<ProjetoCardDTO> listarContainingAndStatus(
            @PathVariable Long secaoId,
            @PathVariable String campoBusca,
            @PathVariable int status
    ) {
        return projetoService.buscarPorNomeEStatus(secaoId, campoBusca, status);
    }

    @GetMapping("/buscar/status/{secaoId}/{status}")
    public List<ProjetoCardDTO> listarComFiltroStatus(
            @PathVariable Long secaoId,
            @PathVariable int status
    ) {
        return projetoService.buscarPorStatus(secaoId, status);
    }

    @GetMapping("/alocados/{secaoId}/{consultorId}")
    public List<ProjetoAlocarDTO> projetosAlocados(
            @PathVariable Long secaoId,
            @PathVariable Long consultorId
    ) {
        return projetoService.buscarProjetosConsultorNaoAlocado(consultorId, secaoId);
    }


    @GetMapping("/buscar/{projetoId}")
    public ProjetoCardDTO buscarPorId(@PathVariable Long projetoId){
          return projetoService.buscarPorId(projetoId);
    }

    @PostMapping("/cadastrar")
    public String cadastrarProjeto(@RequestBody ProjetoInputDTO projeto) {
        return projetoService.cadastrarProjeto(projeto);
    }


}
