package net.weg.gestor.api.model.consultorhoras;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.api.model.usuario.UsuarioDTO;

import java.util.List;

@Getter
@Setter
public class ConsultorComSuasHorasApontadas {

    private UsuarioDTO usuario;

    private List<HoraApontadaDTO> horas;

    private int horasTotais;

    private double totalGasto;

    private double precoHora;

    private boolean podeApontar;

}
