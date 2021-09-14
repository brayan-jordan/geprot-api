package net.weg.gestor.api.model.projetoinputDTO;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProjetoInputDTO {

    @NotNull
    private String nome;

    private String descricao;

    private String nomeSolicitante;

    private String nomeResponsavel;

    private LocalDate dataFinalizacao;

    private LocalDate dataInicio;

    @Size(min = 1)
    private List<AlocarCCPagantesInputDTO> ccpagantes;

    @Size(min = 1)
    private List<AlocarConsultoresInputDTO> consultores;

}
