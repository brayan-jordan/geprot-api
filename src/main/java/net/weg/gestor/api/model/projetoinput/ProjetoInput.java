package net.weg.gestor.api.model.projetoinput;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.api.model.GestorModel;

@Getter
@Setter
public class ProjetoInput {

    private GestorModel gestor;
    private String nomeprojeto;
    private int horasprevistas;
    private double valorprojeto;

}
