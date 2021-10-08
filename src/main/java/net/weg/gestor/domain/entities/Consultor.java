package net.weg.gestor.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "consultores")
public class Consultor {

    @Id
    private Long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuarios_id")
    private Usuario usuario;

    @JoinColumn(name = "fornecedores_id")
    @ManyToOne
    private Fornecedor fornecedor;

    @ManyToMany
    private List<Projeto> projetos;

    private double precoHora;

}
