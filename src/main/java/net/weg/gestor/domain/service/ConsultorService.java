package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.ConsultorAssembler;
import net.weg.gestor.api.map.ConsultoresAlocadosAssembler;
import net.weg.gestor.api.model.ConsultorAlocadoNoProjetoDTO;
import net.weg.gestor.api.model.consultor.ConsultorDTO;
import net.weg.gestor.api.model.consultor.ConsultorNaoAlocadoDTO;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.ConsultorAlocado;
import net.weg.gestor.domain.entities.Projeto;
import net.weg.gestor.domain.entities.Skill;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.ConsultorAlocadoRepository;
import net.weg.gestor.domain.repository.ConsultorRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.SkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@AllArgsConstructor
public class ConsultorService {

    private ConsultorAssembler consultorAssembler;
    private ConsultorRepository consultorRepository;
    private ConsultoresAlocadosAssembler consultoresAlocadosAssembler;
    private SkillRepository skillRepository;
    private ConsultorAlocadoRepository consultorAlocadoRepository;
    private ProjetoRepository projetoRepository;

    @Transactional
    public Consultor cadastrar(Consultor consultor) {
        return consultorRepository.save(consultor);
    }

    public List<ConsultorDTO> buscarConsultoresPorSkill(Long skillId) {
        Skill skill = skillRepository.buscarPorId(skillId);
        List<ConsultorDTO> consultoresComASkill = new ArrayList<>();
        List<Consultor> consultores = consultorRepository.findAll();
        consultores.forEach(consultor -> {
            if (verificaSeConsultorTemCertaSkill(skill, consultor).get()) {
                consultoresComASkill.add(consultorAssembler.toModel(consultor));
            }
        });

        return consultoresComASkill;
    }

    public List<ConsultorAlocadoNoProjetoDTO> buscarConsultorePorProjeto(Long projetoId) {
        List<ConsultorAlocadoNoProjetoDTO> consultoresDto = new ArrayList<>();
        Projeto projeto = projetoRepository.findById(projetoId).orElseThrow(() -> new NegocioException(
                "Projeto nao encontrado"
        ));

        List<ConsultorAlocado> todosConsultoresDoProjeto = consultorAlocadoRepository.consultoresAlocadosProjeto(projeto);

        todosConsultoresDoProjeto.forEach(consultorDoProjeto -> {
            ConsultorAlocadoNoProjetoDTO consultorAlocadoNoProjetoDTO = new ConsultorAlocadoNoProjetoDTO();
            consultorAlocadoNoProjetoDTO.setId(consultorDoProjeto.getConsultor().getId());
            consultorAlocadoNoProjetoDTO.setHorasAlocadas(consultorDoProjeto.getLimiteHoras());
            consultorAlocadoNoProjetoDTO.setNome(consultorDoProjeto.getConsultor().getUsuario().getNome());
            consultorAlocadoNoProjetoDTO.setSkill(consultorDoProjeto.getSkill());
            consultoresDto.add(consultorAlocadoNoProjetoDTO);
        });

        return consultoresDto;

    }

    public AtomicBoolean verificaSeConsultorTemCertaSkill(Skill skill, Consultor consultor) {
        AtomicBoolean temCertaSkill = new AtomicBoolean(false);
        consultor.getSkills().forEach(skill1 -> {
            if (skill1.getNome().equals(skill.getNome())) {
                temCertaSkill.set(true);
            }
        });

        return temCertaSkill;
    }

    public List<ConsultorNaoAlocadoDTO> buscarTodosConsultores() {
        return consultoresAlocadosAssembler.toCollectionModelNaoAlocado(consultorRepository.findAll());
    }

    public ConsultorDTO buscarConsultor(long consultorId) {
        return consultorAssembler.toModel(consultorRepository.findById(consultorId)
                .orElseThrow(() -> new NegocioException("Consultor não encontrado")));
    }

    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorNome(String pesquisaPorNome) {
        List<Consultor> consultoresPesquisados = new ArrayList<>();
        List<Consultor> todosConsultores = consultorRepository.findAll();
        todosConsultores.forEach(consultor -> {
            if (consultor.getUsuario().getNome().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNome.toLowerCase(Locale.ROOT)))
            {
                consultoresPesquisados.add(consultor);
            }
        });

        return consultoresAlocadosAssembler.toCollectionModelNaoAlocado(consultoresPesquisados);
    }

    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorNomeFornecedor(String pesquisaPorNomeFornecedor) {
        List<Consultor> consultoresPesquisados = new ArrayList<>();
        List<Consultor> todosConsultores = consultorRepository.findAll();
        todosConsultores.forEach(consultor -> {
            if (consultor.getFornecedor().getNome().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNomeFornecedor.toLowerCase(Locale.ROOT)))
            {
                consultoresPesquisados.add(consultor);
            }
        });

        return consultoresAlocadosAssembler.toCollectionModelNaoAlocado(consultoresPesquisados);
    }

    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorId(Long pesquisaPorId) {
        List<Consultor> consultoresPesquisados = new ArrayList<>();
        List<Consultor> todosConsultores = consultorRepository.findAll();
        todosConsultores.forEach(consultor -> {
            if (consultor.getId().toString().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorId.toString().toLowerCase(Locale.ROOT)))
            {
                consultoresPesquisados.add(consultor);
            }
        });

        return consultoresAlocadosAssembler.toCollectionModelNaoAlocado(consultoresPesquisados);
    }

    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorIdeNome(
            Long pesquisaPorId, String pesquisaPorNome)
    {
        List<Consultor> consultoresPesquisados = new ArrayList<>();
        List<Consultor> todosConsultores = consultorRepository.findAll();
        todosConsultores.forEach(consultor -> {
            if (consultor.getId().toString().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorId.toString().toLowerCase(Locale.ROOT)) &&
                consultor.getUsuario().getNome().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNome.toLowerCase(Locale.ROOT))
            ) {
                consultoresPesquisados.add(consultor);
            }
        });

        return consultoresAlocadosAssembler.toCollectionModelNaoAlocado(consultoresPesquisados);
    }

    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorIdeNomeFornecedor(
            Long pesquisaPorId, String pesquisaPorNomeFornecedor)
    {
        List<Consultor> consultoresPesquisados = new ArrayList<>();
        List<Consultor> todosConsultores = consultorRepository.findAll();
        todosConsultores.forEach(consultor -> {
            if (consultor.getId().toString().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorId.toString().toLowerCase(Locale.ROOT)) &&
                consultor.getFornecedor().getNome().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNomeFornecedor.toLowerCase(Locale.ROOT))
            ) {
                consultoresPesquisados.add(consultor);
            }
        });

        return consultoresAlocadosAssembler.toCollectionModelNaoAlocado(consultoresPesquisados);
    }

    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorNomeFornecedorENome(
            String pesquisaPorNome, String pesquisaPorNomeFornecedor)
    {
        List<Consultor> consultoresPesquisados = new ArrayList<>();
        List<Consultor> todosConsultores = consultorRepository.findAll();
        todosConsultores.forEach(consultor -> {
            if (consultor.getUsuario().getNome().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNome.toLowerCase(Locale.ROOT)) &&
                consultor.getFornecedor().getNome().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNomeFornecedor.toLowerCase(Locale.ROOT))
            ) {
                consultoresPesquisados.add(consultor);
            }
        });

        return consultoresAlocadosAssembler.toCollectionModelNaoAlocado(consultoresPesquisados);
    }

    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorNomeFornecedorENomeEId(
            String pesquisaPorNome, String pesquisaPorNomeFornecedor, Long pesquisaPorId)
    {
        List<Consultor> consultoresPesquisados = new ArrayList<>();
        List<Consultor> todosConsultores = consultorRepository.findAll();
        todosConsultores.forEach(consultor -> {
            if (consultor.getUsuario().getNome().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNome.toLowerCase(Locale.ROOT)) &&
                consultor.getFornecedor().getNome().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorNomeFornecedor.toLowerCase(Locale.ROOT)) &&
                consultor.getId().toString().toLowerCase(Locale.ROOT).contains(
                    pesquisaPorId.toString().toLowerCase(Locale.ROOT)
                )
            ) {
                consultoresPesquisados.add(consultor);
            }
        });

        return consultoresAlocadosAssembler.toCollectionModelNaoAlocado(consultoresPesquisados);
    }
}
