package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.entities.HoraApontada;
import net.weg.gestor.domain.service.HorasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horas")
@AllArgsConstructor
@Getter
@Setter
public class HorasController {

    private HorasService horasService;



}
