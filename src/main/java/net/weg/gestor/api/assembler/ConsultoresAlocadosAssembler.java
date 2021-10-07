package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.ConsultorNaoAlocadoDTO;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.ConsultoresAlocados;
import net.weg.gestor.domain.repository.ConsultoresAlocadosRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class ConsultoresAlocadosAssembler {

    private ModelMapper modelMapper;
    private ConsultoresAlocadosRepository consultoresAlocadosRepository;

    public ConsultorNaoAlocadoDTO toModelNaoAlocado(Consultor consultorNaoAlocado) {
        ConsultorNaoAlocadoDTO consultorNaoAlocadoDTO = modelMapper.map(consultorNaoAlocado, ConsultorNaoAlocadoDTO.class);
        consultorNaoAlocadoDTO.setQuantidade_projetos_alocado(consultoresAlocadosRepository.todasDemandasAlocadas(consultorNaoAlocado).size());
        return consultorNaoAlocadoDTO;
    }

    public List<ConsultorNaoAlocadoDTO> toCollectionModelNaoAlocado(List<Consultor> consultores) {
        return consultores.stream().map(this::toModelNaoAlocado).collect(Collectors.toList());
    }

}
