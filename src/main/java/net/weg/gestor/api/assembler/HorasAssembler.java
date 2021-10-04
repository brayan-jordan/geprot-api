package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.entities.HorasApontadas;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class HorasAssembler {

    private ModelMapper modelMapper;

//    public ColunaHoraApontadaDTO toModel(HorasApontadas horasApontadas) {
//        return modelMapper.map(horasApontadas, ColunaHoraApontadaDTO.class);
//    }
//
//    public List<ColunaHoraApontadaDTO> toCollectionModel(List<HorasApontadas> horasApontadas) {
//        return horasApontadas.stream().map(this::toModel).collect(Collectors.toList());
//    }
//
//    public HorasApontadas toEntity(ApontamentoDeHoraInputDTO apontamento) {
//        return modelMapper.map(apontamento, HorasApontadas.class);
//    }
//
//    public HorasApontadasTotalDTO toModelTotal(HorasApontadas horasApontadas) {
//        return modelMapper.map(horasApontadas, HorasApontadasTotalDTO.class);
//    }

}
