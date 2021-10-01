package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.api.model.HorasApontadasTotalDTO;
import net.weg.gestor.api.model.ListaApontamentoConsultor;
import net.weg.gestor.api.model.apontarinputDTO.ApontamentoDeHoraInputDTO;
import net.weg.gestor.domain.model.HorasApontadas;
import net.weg.gestor.domain.repository.HorasApontadasRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import net.weg.gestor.domain.service.HorasService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/horas")
@AllArgsConstructor
@Getter
@Setter
public class HorasController {

    private HorasService horasService;

    @GetMapping("/listar")
    List<HorasApontadas> listarTodos() {
        return horasService.listarTodos();
    }

//    @PostMapping("/apontar")
//    String apontamentoConsultor(@RequestBody @Valid ApontamentoDeHoraInputDTO apontamento) {
//        return horasService.apontarHoras(apontamento);
//    }
//
//    @GetMapping("/listar/{projetoId}/{usuarioId}")
//    ListaApontamentoConsultor listarApontamentoConsultor(@PathVariable Long projetoId, @PathVariable Long usuarioId) {
//        return horasService.buscarApontamentoConsultor(projetoId, usuarioId);
//    }

//    @PatchMapping("/aprovar/{projetoId}/{usuarioId}")
//    String aprovarApontamentosConsultor(@PathVariable Long projetoId, @PathVariable Long usuarioId) {
//        return horasService.aprovarApontamentosConsultor(projetoId, usuarioId);
//    }
//
//    @PutMapping("/reprovar/{projetoId}/{usuarioId}")
//    String reprovarApontamentosConsultor(@PathVariable Long projetoId, @PathVariable Long usuarioId) {
//        return horasService.reprovarApontamentosConsultor(projetoId, usuarioId);
//    }

//    @GetMapping("/listar/{projetoId}")
//    ArrayList<HorasApontadasTotalDTO> teste(@PathVariable Long projetoId) {
//        return horasService.getApontamentoTotal(projetoId);
//    }

}
