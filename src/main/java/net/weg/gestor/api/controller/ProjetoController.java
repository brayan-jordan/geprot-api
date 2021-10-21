package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.ProjetoAlocarDTO;
import net.weg.gestor.api.model.ProjetoCardDTO;
import net.weg.gestor.api.model.ProjetoDetalhadoDTO;
import net.weg.gestor.api.model.cadastrarprojetoinput.ProjetoInputDTO;
import net.weg.gestor.domain.service.ConsultoresAlocadosService;
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

    @GetMapping("/buscar/nome/{secaoId}/{campoBusca}")
    public List<ProjetoCardDTO> buscarPorNome(@PathVariable Long secaoId, @PathVariable String campoBusca) {
        return projetoService.buscarPorNome(secaoId, campoBusca);
    }

    @GetMapping("/buscar/responsavel/{secaoId}/{campoBusca}")
    public List<ProjetoCardDTO> buscarPorNomeResponsavel(@PathVariable Long secaoId, @PathVariable String campoBusca) {
        return projetoService.buscarPorNomeResponsavel(secaoId, campoBusca);
    }

        @GetMapping("/buscar/nomeestatus/{secaoId}/{campoBusca}/{status}")
    public List<ProjetoCardDTO> listarContainingAndStatus(@PathVariable Long secaoId, @PathVariable String campoBusca, @PathVariable int status) {
        return projetoService.buscarPorNomeEStatus(secaoId, campoBusca, status);
    }

    @GetMapping("/buscar/status/{secaoId}/{status}")
    public List<ProjetoCardDTO> listarComFiltroStatus(@PathVariable Long secaoId, @PathVariable int status) {
        return projetoService.buscarPorStatus(secaoId, status);
    }

    @GetMapping("/alocados/{secaoId}/{consultorId}")
    public List<ProjetoAlocarDTO> projetosAlocados(@PathVariable Long secaoId , @PathVariable Long consultorId) {
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
