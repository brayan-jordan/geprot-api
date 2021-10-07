package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.CCPaganteDTO;
import net.weg.gestor.api.model.ProjetoCardDTO;
import net.weg.gestor.api.model.ProjetoDetalhadoDTO;
import net.weg.gestor.domain.entities.CCPagantes;
import net.weg.gestor.domain.entities.Projeto;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.ConsultoresAlocadosRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
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
    private ConsultoresAlocadosRepository consultoresAlocadosRepository;
    private CCPagantesRepository ccPagantesRepository;


    public ProjetoCardDTO toModel(Projeto projeto) {
        ProjetoCardDTO projetoCardDTO = modelMapper.map(projeto, ProjetoCardDTO.class);
        projetoCardDTO.setValorRestante(projetoCardDTO.getValor() - projetoCardDTO.getValorUtilizado());
        return projetoCardDTO;
    }

    public List<ProjetoCardDTO> toCollectionModel(List<Projeto> projetos) {
        return projetos.stream().map(this::toModel).collect(Collectors.toList());
    }

    public ProjetoDetalhadoDTO toModelDetalhada(Projeto projeto, List<CCPagantes> ccPagantes) {
        ProjetoDetalhadoDTO projetoDetalhado = modelMapper.map(projeto, ProjetoDetalhadoDTO.class);
        projetoDetalhado.setHorasRestantes(projetoDetalhado.getHorasPrevistas() - projetoDetalhado.getHorasTrabalhadas());
        projetoDetalhado.setValorRestante(projetoDetalhado.getValor() - projetoDetalhado.getValorUtilizado());
        return projetoDetalhado;

    }
    

}
