package net.weg.gestor.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "gestores")
public class Gestor {

    @Id
    private Long id;

    @NotNull
    @JoinColumn(name = "secoes_id")
    @ManyToOne
    private Secao secao;

    @JoinColumn(name = "usuarios_id")
    @OneToOne
    private Usuario usuario;

}
