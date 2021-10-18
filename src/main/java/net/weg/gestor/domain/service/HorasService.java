package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.ConsultorAssembler;
import net.weg.gestor.api.map.HorasAssembler;
import net.weg.gestor.api.model.ConsultorAlocadoDTO;
import net.weg.gestor.domain.entities.*;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.weg.gestor.domain.entities.StatusApontamento.REPROVADO;

@Service
@AllArgsConstructor
public class HorasService {

    private HoraApontadaRepository horaApontadaRepository;
    private ConsultorAlocadoRepository consultorAlocadoRepository;
    private ConsultorAssembler consultorAssembler;
    private ConsultorRepository consultorRepository;
    private ProjetoRepository projetoRepository;
    private UsuarioRepository usuarioRepository;
    private HorasAssembler horasAssembler;
    private ComponentsService componentsService;

    public List<HoraApontada> listarTodos() {
        return horaApontadaRepository.findAll();
    }

    public List<ConsultorAlocadoDTO> buscarConsultoresAlocadosProjeto(Long projetoId) {
        Projeto projeto = projetoRepository.findById(projetoId).orElseThrow(() -> new NegocioException("a"));
        List<ConsultorAlocadoDTO> consultores = consultorAssembler.toCollectionModelAlocado(consultorAlocadoRepository.consultoresAlocadosProjeto(projeto));
        consultores.forEach(consultor -> {
            if (horaApontadaRepository.buscarHorasReprovadasConsultor(consultorRepository.getById(consultor.getId())).size() > 0) {
                consultor.setStatusApontamento(REPROVADO);
                return;
            }
            if (horaApontadaRepository.buscarHorasPendentesConsultor(consultorRepository.getById(consultor.getId())).size() > 0) {
                consultor.setStatusApontamento(StatusApontamento.PENDENTE);
                return;
            }
            consultor.setStatusApontamento(StatusApontamento.APROVADO);
        });

        return consultores;
    }

}
