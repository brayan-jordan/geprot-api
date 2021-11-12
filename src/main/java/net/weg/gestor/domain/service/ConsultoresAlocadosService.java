package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.ConsultoresAlocadosAssembler;
import net.weg.gestor.api.model.consultor.ConsultorNaoAlocadoDTO;
import net.weg.gestor.api.model.input.AlocarConsultorInputDTO;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.ConsultorAlocado;
import net.weg.gestor.domain.entities.Projeto;
import net.weg.gestor.domain.entities.Skill;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@Service
public class ConsultoresAlocadosService {

    private ConsultorAlocadoRepository consultorAlocadoRepository;
    private ConsultorRepository consultorRepository;
    private ConsultoresAlocadosAssembler consultoresAlocadosAssembler;
    private ProjetoRepository projetoRepository;
    private SkillRepository skillRepository;


    public String alocarConsultor(AlocarConsultorInputDTO alocar) {
        Consultor consultor = consultorRepository.findById(alocar.getConsultorId()).orElseThrow(
                () -> new NegocioException("Consultor nao encontrado")
        );
        Projeto projeto = projetoRepository.findById(alocar.getProjetoId()).orElseThrow(
                () -> new NegocioException("Projeto nao encontrado")
        );

        if (verificaSeConsultorEstaAlocadoEmProjeto(projeto, consultor)) {
            throw new NegocioException("Esse consultor já estava alocado ao projeto, tente, verifique os dados informados");
        }

        Skill skillParaAlocar = skillRepository.buscarPorId((long) alocar.getNumeroSkill());

        consultorAlocadoRepository.save(new ConsultorAlocado(projeto, consultor, alocar.getQuantidadeHoras(), skillParaAlocar));
        return "Consultor alocado com sucesso";
    }

    public boolean verificaSeConsultorEstaAlocadoEmProjeto(Projeto projeto, Consultor consultor) {
        return consultorAlocadoRepository.verificaSeConsultorEstaAlocado(consultor, projeto).isPresent();
    }

}
