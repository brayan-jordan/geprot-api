package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.api.model.ProjetoDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjetoInput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProjetoAssembler {

    private ModelMapper modelMapper;

    public Projeto toEntity(ProjetoInput projetoInput) {
        return modelMapper.map(projetoInput, Projeto.class);

    }

    public ProjetoDTO toModel(Projeto projeto) {
        return modelMapper.map(projeto, ProjetoDTO.class);
    }
}
