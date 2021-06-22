package net.weg.gestor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.weg.gestor.domain.service.ValidationGroups;

import javax.persistence.*;
import javax.persistence.criteria.From;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ccpagantes")
public class CentroDeCusto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(groups = ValidationGroups.Idcentrodecusto.class)
    private Long idcentrodecusto;

    private String nomecentrodecusto;

}
