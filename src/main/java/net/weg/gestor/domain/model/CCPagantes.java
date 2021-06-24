package net.weg.gestor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.weg.gestor.domain.service.ValidationGroups;

import javax.persistence.*;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CCPagantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ConvertGroup(from = Default.class, to = ValidationGroups.Idcentrodecusto.class)
    @ManyToOne
    private CentroDeCusto centrodecusto;

    @ManyToOne
    @ConvertGroup(from = Default.class, to = ValidationGroups.Idprojeto.class)
    private Projeto projeto;

    private int taxa;

}
