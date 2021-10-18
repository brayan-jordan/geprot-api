package net.weg.gestor.api.map;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.consultorhoras.HoraApontadaDTO;
import net.weg.gestor.domain.entities.HoraApontada;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class HorasAssembler {

    private ModelMapper modelMapper;

    public HoraApontadaDTO toModel(HoraApontada horaApontada) {
        return modelMapper.map(horaApontada, HoraApontadaDTO.class);
    }

    public List<HoraApontadaDTO> toCollectionModel(List<HoraApontada> horasApontadas) {
        return  horasApontadas.stream().map(this::toModel).collect(Collectors.toList());
    }

}
