package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.DashboardConcluidos;
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
        return projetoService.alocBuscarProjeto(consultorId, secaoId);
    }

    @GetMapping("/alocados/nome/{secaoId}/{consultorId}/{nome}")
    public List<ProjetoAlocarDTO> projetosAlocadosComNome(
            @PathVariable Long secaoId,
            @PathVariable Long consultorId,
            @PathVariable String nome
    ) {
        return projetoService.alocBuscarProjetoComNomeProjeto(consultorId, secaoId, nome);
    }

    @GetMapping("/alocados/nomeresponsavel/{secaoId}/{consultorId}/{nomeresponsavel}")
    public List<ProjetoAlocarDTO> projetosAlocadosComNomeResponsavel(
            @PathVariable Long secaoId,
            @PathVariable Long consultorId,
            @PathVariable String nomeresponsavel
    ) {
        return projetoService.alocBuscarProjetoComNomeResponsavel(consultorId, secaoId, nomeresponsavel);
    }

    @GetMapping("/alocados/nomeresponsavel/nome/{secaoId}/{consultorId}/{nomeresponsavel}/{nome}")
    public List<ProjetoAlocarDTO> projetosAlocadosComNomeResponsavelENome(
            @PathVariable Long secaoId,
            @PathVariable Long consultorId,
            @PathVariable String nomeresponsavel,
            @PathVariable String nome
    ) {
        return projetoService.alocBuscarProjetoComNomeeNomeResponsavel(consultorId, secaoId, nome, nomeresponsavel);
    }

    @GetMapping("/alocados/id/nome/{secaoId}/{consultorId}/{id}/{nome}")
    public List<ProjetoAlocarDTO> projetosAlocadosComNomeeId(
            @PathVariable Long secaoId,
            @PathVariable Long consultorId,
            @PathVariable String id,
            @PathVariable String nome
    ) {
        return projetoService.alocBuscarProjetoComIdeNome(consultorId, secaoId, id, nome);
    }

    @GetMapping("/alocados/id/nomeresponsavel/{secaoId}/{consultorId}/{id}/{nomeresponsavel}")
    public List<ProjetoAlocarDTO> projetosAlocadosComNomeResponsaveleId(
            @PathVariable Long secaoId,
            @PathVariable Long consultorId,
            @PathVariable String id,
            @PathVariable String nomeresponsavel
    ) {
        return projetoService.alocBuscarProjetoComIdeNomeResponsavel(consultorId, secaoId, id, nomeresponsavel);
    }

    @GetMapping("/alocados/id/nomeresponsavel/nome/{secaoId}/{consultorId}/{id}/{nomeresponsavel}/{nome}")
    public List<ProjetoAlocarDTO> projetosAlocadosComNomeResponsaveleId(
            @PathVariable Long secaoId,
            @PathVariable Long consultorId,
            @PathVariable String id,
            @PathVariable String nomeresponsavel,
            @PathVariable String nome
    ) {
        return projetoService.alocBuscarProjetoComNomeeNomeResponsaveleId(consultorId, secaoId, nome, nomeresponsavel, id);
    }

    @GetMapping("/alocados/id/{secaoId}/{consultorId}/{id}")
    public List<ProjetoAlocarDTO> projetosAlocadosComId(
            @PathVariable Long secaoId,
            @PathVariable Long consultorId,
            @PathVariable String id
    ) {
        return projetoService.alocBuscarProjetoComId(consultorId, secaoId, id);
    }


    @GetMapping("/buscar/{projetoId}")
    public ProjetoCardDTO buscarPorId(@PathVariable Long projetoId){
          return projetoService.buscarPorId(projetoId);
    }

    @PostMapping("/cadastrar")
    public String cadastrarProjeto(@RequestBody ProjetoInputDTO projeto) {
        return projetoService.cadastrarProjeto(projeto);
    }

    @GetMapping("/dashboard/7dias/{secaoId}")
    public List<DashboardConcluidos> buscarUltimos7dias(@PathVariable Long secaoId) {
        return projetoService.concluidosUltimos7Dias(secaoId);
    }

}
