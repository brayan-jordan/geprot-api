package net.weg.gestor.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.api.model.usuario.UsuarioDTO;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class NotificacaoDTO {

    private Long id;

    private String descricao;

    private UsuarioDTO usuario;

    private LocalDate data;

    private boolean statusLeitura;


}
