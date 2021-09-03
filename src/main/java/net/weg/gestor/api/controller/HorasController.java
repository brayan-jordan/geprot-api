package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.service.HorasService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HorasController {

    private HorasService horasService;

}
