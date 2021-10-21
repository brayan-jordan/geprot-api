package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.ConsultoresAlocadosAssembler;
import net.weg.gestor.api.model.ConsultorNaoAlocadoDTO;
import net.weg.gestor.api.model.input.AlocarConsultorInputDTO;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.ConsultorAlocado;
import net.weg.gestor.domain.entities.Projeto;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.ConsultorRepository;
import net.weg.gestor.domain.repository.ConsultorAlocadoRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
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
    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;

    public List<ConsultorNaoAlocadoDTO> buscarConsultores() {
        return consultoresAlocadosAssembler.toCollectionModelNaoAlocado(consultorRepository.findAll());

    }

    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorNome(String pesquisa) {
        List<Consultor> consultoresPesquisados = new ArrayList<>();
        List<Consultor> todosConsultores = consultorRepository.findAll();
        todosConsultores.forEach(consultor -> {
            if (consultor.getUsuario().getNome().toLowerCase(Locale.ROOT).contains(pesquisa.toLowerCase(Locale.ROOT))) {
                consultoresPesquisados.add(consultor);
            }
        });

        return consultoresAlocadosAssembler.toCollectionModelNaoAlocado(consultoresPesquisados);
    }

    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorNomeFornecedor(String pesquisa) {
        List<Consultor> consultoresPesquisados = new ArrayList<>();
        List<Consultor> todosConsultores = consultorRepository.findAll();
        todosConsultores.forEach(consultor -> {
            if (consultor.getFornecedor().getNome().toLowerCase(Locale.ROOT).contains(pesquisa.toLowerCase(Locale.ROOT))) {
                consultoresPesquisados.add(consultor);
            }
        });

        return consultoresAlocadosAssembler.toCollectionModelNaoAlocado(consultoresPesquisados);
    }

    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorId(Long id) {
        List<Consultor> consultoresPesquisados = new ArrayList<>();
        List<Consultor> todosConsultores = consultorRepository.findAll();
        todosConsultores.forEach(consultor -> {
            if (consultor.getId().equals(id)) {
                consultoresPesquisados.add(consultor);
            }
        });

        return consultoresAlocadosAssembler.toCollectionModelNaoAlocado(consultoresPesquisados);
    }

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

        consultorAlocadoRepository.save(new ConsultorAlocado(projeto, consultor, alocar.getQuantidadeHoras()));
        return "Consultor alocado com sucesso";
    }

    public boolean verificaSeConsultorEstaAlocadoEmProjeto(Projeto projeto, Consultor consultor) {
        return consultorAlocadoRepository.verificaSeConsultorEstaAlocado(consultor, projeto).isPresent();
    }

}
