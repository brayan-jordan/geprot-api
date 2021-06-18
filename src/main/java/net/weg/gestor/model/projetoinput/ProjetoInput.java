package net.weg.gestor.model.projetoinput;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.model.GestorModel;

@Getter
@Setter
public class ProjetoInput {

    private GestorModel gestor;
    private String nomeprojeto;
    private int horasprevistas;
    private double valorprojeto;

}
