package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.SecaoDTO;
import net.weg.gestor.domain.entities.Secao;
import net.weg.gestor.domain.repository.SecaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SecaoAssembler {

    private SecaoRepository secaoRepository;
    private ModelMapper modelMapper;

    public SecaoDTO toModel(Secao secao) {
        return modelMapper.map(secao, SecaoDTO.class);
    }

}
