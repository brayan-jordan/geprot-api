package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.api.model.HorasApontadasTotalDTO;
import net.weg.gestor.domain.model.HorasApontadas;
import net.weg.gestor.domain.model.StatusApontamento;
import net.weg.gestor.domain.repository.HorasApontadasRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import net.weg.gestor.domain.service.HorasService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/horas")
@AllArgsConstructor
@Getter
@Setter
public class HorasController {

    private HorasService horasService;
    private ProjetoRepository projetoRepository;
    private UsuarioRepository usuarioRepository;
    private HorasApontadasRepository horasApontadasRepository;

    @GetMapping("/listar")
    List<HorasApontadas> listarTodos() {
        return horasService.listarTodos();
    }

    @PostMapping("/teste")
    HorasApontadas teste() {
        HorasApontadas horasApontadas = new HorasApontadas();
        horasApontadas.setData_hora(LocalDate.now());
        horasApontadas.setQuantidade_horas(200);
        horasApontadas.setProjeto(projetoRepository.findByIdProjeto(1));
        horasApontadas.setUsuario(usuarioRepository.findByIdUsuario(67264));
        horasApontadas.setDescricao_trabalho("Ola");
        horasApontadas.setStatus(StatusApontamento.RECUSADO);
        return horasApontadasRepository.save(horasApontadas);
    }

//    @GetMapping("/listar/{projetoId}")
//    List<HorasApontadas> listarPorProjeto(@PathVariable Long projetoId) {
//        return horasService.listarDeUmProjeto(projetoId);
//    }

    @GetMapping("/listar/{projetoId}")
    ArrayList<HorasApontadasTotalDTO> listarPorProjeto(@PathVariable Long projetoId) {
        return horasService.apontamentoTotal(projetoId);
    }
}
