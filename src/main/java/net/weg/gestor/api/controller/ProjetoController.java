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
    private ConsultoresAlocadosService consultoresAlocadosService;

    @GetMapping("/listar/{secaoId}")
    public List<ProjetoCardDTO> listarPorSecao(@PathVariable Long secaoId) {
        return projetoService.listarPorSecao(secaoId);
    }

    @GetMapping("/listar/string/{secaoId}/{campoBusca}")
    public List<ProjetoCardDTO> buscarPorNome(@PathVariable Long secaoId, @PathVariable String campoBusca) {
        return projetoService.buscarPorNome(secaoId, campoBusca);
    }

    @GetMapping("/listar/stringandstatus/{secaoId}/{campoBusca}/{status}")
    public List<ProjetoCardDTO> listarContainingAndStatus(@PathVariable Long secaoId, @PathVariable String campoBusca, @PathVariable int status) {
        return projetoService.buscarPorNomeEStatus(secaoId, campoBusca, status);
    }

    @GetMapping("/listar/status/{secaoId}/{status}")
    public List<ProjetoCardDTO> listarComFiltroStatus(@PathVariable Long secaoId, @PathVariable int status) {
        return projetoService.buscarPorStatus(secaoId, status);
    }

    @GetMapping("/buscar/{secaoId}/{projetoId}")
    public ProjetoDetalhadoDTO buscarInfoProjeto(@PathVariable Long secaoId, @PathVariable Long projetoId) {
        return projetoService.buscarProjeto(secaoId, projetoId);
    }

    @GetMapping("/alocados/{secaoId}/{consultorId}")
    public List<ProjetoAlocarDTO> projetosAlocados(@PathVariable Long secaoId , @PathVariable Long consultorId) {
        return projetoService.buscarProjetosConsultorNaoAlocado(consultorId, secaoId);
    }


    @GetMapping("/buscar/{projetoId}")
    public ProjetoCardDTO listarProjetoID(@PathVariable Long projetoId){
          return projetoService.listarPorId(projetoId);
    }

    @PostMapping("/cadastrar")
    public String cadastrarProjeto(@RequestBody ProjetoInputDTO projeto) {
        return projetoService.cadastrarProjeto(projeto);
    }


}
