package net.weg.gestor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.weg.gestor.domain.service.ValidationGroups;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "centrodecusto")
public class CentroDeCusto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(groups = ValidationGroups.Codigo.class)
    private Long codigo;

    private String nomecentrodecusto;

}
