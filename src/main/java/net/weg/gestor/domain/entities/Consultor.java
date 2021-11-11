package net.weg.gestor.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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

    @JoinColumn(name = "fornecedores_id")
    @ManyToOne
    private Fornecedor fornecedor;

    @ManyToMany
    private List<Projeto> projetos;

    @ManyToMany
    @JoinTable(name = "skills_consultores", joinColumns = @JoinColumn(name = "consultores_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id"))
    private List<Skill> skills;

    private double precoHora;

}
