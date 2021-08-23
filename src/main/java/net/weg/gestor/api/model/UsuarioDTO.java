package net.weg.gestor.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.model.Secao;

@Getter
@Setter
public class UsuarioDTO {

    private long id;
    private String nome;
    private String email;
    @JsonIgnore
    private String senha;
    
    private Secao secao;

}
