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

import java.util.List;

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

    public String alocarConsultor(AlocarConsultorInputDTO alocar) {
        Consultor consultor = consultorRepository.findById(alocar.getConsultorId()).orElseThrow(
                () -> new NegocioException("Consultor nao encontrado"));
        Projeto projeto = projetoRepository.findById(alocar.getProjetoId()).orElseThrow(
                () -> new NegocioException("Projeto nao encontrado"));

        consultorAlocadoRepository.save(new ConsultorAlocado(projeto, consultor, alocar.getQuantidadeHoras()));

        return "deu boa";
    }

    public boolean verifyConsultorIsAllocatedInProject(Projeto projeto, Consultor consultor) {
        return consultorAlocadoRepository.verificaSeConsultorEstaAlocado(consultor, projeto).isPresent();
    }

}
