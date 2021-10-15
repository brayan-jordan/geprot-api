package net.weg.gestor.api.map;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.ConsultorAlocadoDTO;
import net.weg.gestor.api.model.ConsultorDTO;
import net.weg.gestor.api.model.input.ConsultorInputDTO;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.ConsultorAlocado;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ConsultorAssembler {

    private ModelMapper modelMapper;

    public Consultor toEntity(ConsultorInputDTO consultorInputDTO){
        return modelMapper.map(consultorInputDTO, Consultor.class);
    }

    public List<ConsultorDTO> toCollectionModel(List<Consultor> consultores){
        return consultores.stream().map(this::toModel).collect(Collectors.toList());
    }

    public ConsultorDTO toModel(Consultor consultor){
        return modelMapper.map(consultor, ConsultorDTO.class);
    }

    public ConsultorAlocadoDTO toModelConsultorAlocado(ConsultorAlocado consultorAlocado) {
       ConsultorDTO consultor = this.toModel(consultorAlocado.getConsultor());
       return new ConsultorAlocadoDTO(consultor.getUsuario().getNome(),
               consultor.getId(),
               consultor.getFornecedor(),
               consultorAlocado.getLimiteHoras(),
               consultorAlocado.getHorasApontadas()
           );
    }

    public List<ConsultorAlocadoDTO> toCollectionModelAlocado(List<ConsultorAlocado> consultoresAlocados) {
        return consultoresAlocados.stream().map(this::toModelConsultorAlocado).collect(Collectors.toList());
    }

}
