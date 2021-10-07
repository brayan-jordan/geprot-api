package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HorasAssembler {

    private ModelMapper modelMapper;

//    public ColunaHoraApontadaDTO toModel(HoraApontada horasApontadas) {
//        return modelMapper.map(horasApontadas, ColunaHoraApontadaDTO.class);
//    }
//
//    public List<ColunaHoraApontadaDTO> toCollectionModel(List<HoraApontada> horasApontadas) {
//        return horasApontadas.stream().map(this::toModel).collect(Collectors.toList());
//    }
//
//    public HoraApontada toEntity(ApontamentoDeHoraInputDTO apontamento) {
//        return modelMapper.map(apontamento, HoraApontada.class);
//    }
//
//    public HorasApontadasTotalDTO toModelTotal(HoraApontada horasApontadas) {
//        return modelMapper.map(horasApontadas, HorasApontadasTotalDTO.class);
//    }

}
