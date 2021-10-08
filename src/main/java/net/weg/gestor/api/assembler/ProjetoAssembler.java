package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.ProjetoAlocarDTO;
import net.weg.gestor.api.model.ProjetoCardDTO;
import net.weg.gestor.api.model.ProjetoDetalhadoDTO;
import net.weg.gestor.domain.entities.CCPagantes;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.Projeto;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.ConsultorAlocadoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import net.weg.gestor.domain.service.ConsultoresAlocadosService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProjetoAssembler {

    private ModelMapper modelMapper;
    private UsuarioRepository usuarioRepository;
    private CCPagantesRepository ccPagantesRepository;
    private ConsultoresAlocadosService consultoresAlocadosService;


    public ProjetoCardDTO toModel(Projeto projeto) {
        ProjetoCardDTO projetoCardDTO = modelMapper.map(projeto, ProjetoCardDTO.class);
        projetoCardDTO.setValorRestante(projetoCardDTO.getValor() - projetoCardDTO.getValorUtilizado());
        return projetoCardDTO;
    }

    public List<ProjetoCardDTO> toCollectionModel(List<Projeto> projetos) {
        return projetos.stream().map(this::toModel).collect(Collectors.toList());
    }

    public ProjetoAlocarDTO toModelAlocado(Projeto projeto, Consultor consultor) {
        ProjetoAlocarDTO projetoAlocar = modelMapper.map(projeto, ProjetoAlocarDTO.class);
        projetoAlocar.setAllocated(consultoresAlocadosService.verifyConsultorIsAllocatedInProject(projeto, consultor));
        return projetoAlocar;
    }

    public List<ProjetoAlocarDTO> toCollectionModelAlocado(List<Projeto> projetos, Consultor consultor) {
        ArrayList<ProjetoAlocarDTO> listReturnProjetos = new ArrayList<>();
        projetos.forEach(projeto -> {
            listReturnProjetos.add(toModelAlocado(projeto, consultor));
        });
        return listReturnProjetos;
    }

    public ProjetoDetalhadoDTO toModelDetalhada(Projeto projeto, List<CCPagantes> ccPagantes) {
        ProjetoDetalhadoDTO projetoDetalhado = modelMapper.map(projeto, ProjetoDetalhadoDTO.class);
        projetoDetalhado.setHorasRestantes(projetoDetalhado.getHorasPrevistas() - projetoDetalhado.getHorasTrabalhadas());
        projetoDetalhado.setValorRestante(projetoDetalhado.getValor() - projetoDetalhado.getValorUtilizado());
        return projetoDetalhado;

    }


}
