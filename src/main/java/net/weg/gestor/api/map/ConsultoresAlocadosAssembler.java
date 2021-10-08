package net.weg.gestor.api.map;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.ConsultorNaoAlocadoDTO;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.repository.ConsultorAlocadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class ConsultoresAlocadosAssembler {

    private ModelMapper modelMapper;
    private ConsultorAlocadoRepository consultorAlocadoRepository;

    public ConsultorNaoAlocadoDTO toModelNaoAlocado(Consultor consultorNaoAlocado) {
        ConsultorNaoAlocadoDTO consultorNaoAlocadoDTO = modelMapper.map(consultorNaoAlocado, ConsultorNaoAlocadoDTO.class);
        consultorNaoAlocadoDTO.setNome(consultorNaoAlocado.getUsuario().getNome());
        consultorNaoAlocadoDTO.setQuantidade_projetos_alocado(consultorAlocadoRepository.todasDemandasAlocadas(consultorNaoAlocado).size());
        return consultorNaoAlocadoDTO;
    }

    public List<ConsultorNaoAlocadoDTO> toCollectionModelNaoAlocado(List<Consultor> consultores) {
        return consultores.stream().map(this::toModelNaoAlocado).collect(Collectors.toList());
    }

}
