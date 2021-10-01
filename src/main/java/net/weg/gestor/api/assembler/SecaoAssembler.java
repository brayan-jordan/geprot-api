package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.SecoesDTO;
import net.weg.gestor.domain.model.Secao;
import net.weg.gestor.domain.repository.SecaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SecaoAssembler {

    private SecaoRepository secaoRepository;
    private ModelMapper modelMapper;

    public SecoesDTO toModel(Long id) {
        Secao secao = secaoRepository.findByIdAux(id);
        return modelMapper.map(secao, SecoesDTO.class);
    }

}
