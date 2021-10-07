package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.CCPaganteDTO;
import net.weg.gestor.domain.entities.CCPagantes;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.SecaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CCPagantesAssembler {

    private ModelMapper modelMapper;
    private ProjetoRepository projetoRepository;
    private SecaoRepository secaoRepository;

    public CCPaganteDTO toModel(CCPagantes ccPagante) {
        return modelMapper.map(ccPagante, CCPaganteDTO.class);
    }

    public List<CCPaganteDTO> toCollectionModel(List<CCPagantes> ccPagantes) {
        return ccPagantes.stream().map(this::toModel).collect(Collectors.toList());
    }

}
