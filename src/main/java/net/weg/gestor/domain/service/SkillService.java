package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.SkillAssembler;
import net.weg.gestor.api.model.input.SkillDTO;
import net.weg.gestor.api.model.input.SkillInput;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.Skill;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.ConsultorRepository;
import net.weg.gestor.domain.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SkillService {

    private SkillRepository skillRepository;
    private ConsultorRepository consultorRepository;
    private SkillAssembler skillAssembler;

    public List<Skill> buscarSkillsSelecionadas(List<SkillInput> idDasSkills) {
        List<Skill> skillsSelecionadas = new ArrayList<>();
        idDasSkills.forEach(skillInput -> {
            skillsSelecionadas.add(skillRepository.buscarPorId(skillInput.getId()));
        });

        return skillsSelecionadas;
    }

    public List<SkillDTO> listarSkills(){
        return skillAssembler.toCollectionModel(skillRepository.findAll());
    }

    public List<SkillDTO> listarSkillsConsultor(Long consultorId) {
        Consultor consultor = consultorRepository.findById(consultorId).orElseThrow(() -> new NegocioException("Consultor nao encontrado"));
        return skillAssembler.toCollectionModel(consultor.getSkills());
    }

    public SkillDTO buscarSkillPeloId(Long skillId) {
        Skill skill = skillRepository.buscarPorId(skillId);
        return skillAssembler.toModel(skill);
    }
}
