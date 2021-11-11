package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.ConsultorAssembler;
import net.weg.gestor.api.map.ConsultoresAlocadosAssembler;
import net.weg.gestor.api.model.consultor.ConsultorDTO;
import net.weg.gestor.api.model.consultor.ConsultorNaoAlocadoDTO;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.ConsultorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class ConsultorService {

    private ConsultorAssembler consultorAssembler;
    private ConsultorRepository consultorRepository;
    private ConsultoresAlocadosAssembler consultoresAlocadosAssembler;
    private SkillService skillService;

    @Transactional
    public Consultor cadastrar(Consultor consultor) {
        return consultorRepository.save(consultor);
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
