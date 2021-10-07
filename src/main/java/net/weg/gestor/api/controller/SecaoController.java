package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.SecaoDTO;
import net.weg.gestor.domain.service.SecaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secao")
@AllArgsConstructor
public class SecaoController {

    private SecaoService secaoService;

    @GetMapping("/listar")
    public List<SecaoDTO> listarTodas(){
        return secaoService.listarTodas();
    }


//    @GetMapping("/listar/{secaoId}")
//    public DashboardSecaoDTO listarDashboard(@PathVariable Long secaoId){
//        return secaoService.listarDashboard(secaoId);
//    }
}
