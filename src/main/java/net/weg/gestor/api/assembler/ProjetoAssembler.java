package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.api.model.ProjetoDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjetoInputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProjetoAssembler {

    private ModelMapper modelMapper;

    public Projeto toEntity(ProjetoInputDTO projetoInputDTO) {
        return modelMapper.map(projetoInputDTO, Projeto.class);

    }

    public ProjetoDTO toModel(Projeto projeto) {
        return modelMapper.map(projeto, ProjetoDTO.class);
    }

    public List<ProjetoDTO> toCollectionModel(List<Projeto> projetos) {
        return projetos.stream().map(this::toModel).collect(Collectors.toList());
    }
}
