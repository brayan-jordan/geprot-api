package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.ConsultorNaoAlocadoDTO;
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

    @GetMapping("/buscar/{secaoId}/{projetoId}")
    public ProjetoDetalhadoDTO buscarInfoProjeto(@PathVariable Long secaoId, @PathVariable Long projetoId) {
        return projetoService.buscarProjeto(secaoId, projetoId);
    }

    @GetMapping("/alocados/{consultorId}")
    public List<ProjetoAlocarDTO> projetosAlocados(@PathVariable Long consultorId) {
        return projetoService.buscarIfConsultorNotAlocatted(consultorId);
    }

    @PostMapping("/cadastrar")
    public String cadastrarProjeto(@RequestBody ProjetoInputDTO projeto) {
        return projetoService.cadastrarProjeto(projeto);
    }

}
