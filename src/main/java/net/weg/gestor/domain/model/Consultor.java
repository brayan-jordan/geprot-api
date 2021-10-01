package net.weg.gestor.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuarios_id")
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fornecedores_id")
    private Fornecedor fornecedor;

    @ManyToMany
    @JoinTable(name = "consultores_alocados", joinColumns =
    @JoinColumn(name = "consultores_id", referencedColumnName = "id"), inverseJoinColumns =
    @JoinColumn(name = "projetos_id", referencedColumnName = "id"))
    private List<Projeto> projetos;

    private double precoHora;

}
