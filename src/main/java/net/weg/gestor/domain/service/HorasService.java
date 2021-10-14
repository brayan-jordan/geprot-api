package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.ConsultorAssembler;
import net.weg.gestor.api.map.HorasAssembler;
import net.weg.gestor.api.model.ConsultorAlocadoDTO;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.ConsultorAlocado;
import net.weg.gestor.domain.entities.HoraApontada;
import net.weg.gestor.domain.entities.Projeto;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.ConsultorAlocadoRepository;
import net.weg.gestor.domain.repository.HoraApontadaRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HorasService {

    private HoraApontadaRepository horaApontadaRepository;
    private ConsultorAlocadoRepository consultorAlocadoRepository;
    private ConsultorAssembler consultorAssembler;
    private ProjetoRepository projetoRepository;
    private UsuarioRepository usuarioRepository;
    private HorasAssembler horasAssembler;
    private ComponentsService componentsService;

    public List<HoraApontada> listarTodos() {
        return horaApontadaRepository.findAll();
    }

    public List<ConsultorAlocadoDTO> buscarConsultoresAlocadosProjeto(Long projetoId) {
        Projeto projeto = projetoRepository.findById(projetoId).orElseThrow(() -> new NegocioException("a"));
        List<ConsultorAlocado> consultorAlocados = consultorAlocadoRepository.consultoresAlocadosProjeto(projeto);

    }

}
