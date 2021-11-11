package net.weg.gestor.api.map;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.consultor.ConsultorDTO;
import net.weg.gestor.api.model.input.SkillDTO;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.Skill;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SkillAssembler {

    ModelMapper modelMapper;

    public List<SkillDTO> toCollectionModel(List<Skill> skills){
        return skills.stream().map(this::toModel).collect(Collectors.toList());
    }

    public SkillDTO toModel(Skill skill){
        return modelMapper.map(skill, SkillDTO.class);
    }
}
