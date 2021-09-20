package net.weg.gestor.api.model.usuarioinputDTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

@Getter
@Setter
public class UsuarioInputDTO {

    private String nome;

    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    private FornecedorInputDTO idFornecedor;

    private SecaoInputDTO secao;

    private double precoHora;

}
