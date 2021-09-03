package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.api.model.HorasApontadasTotalDTO;
import net.weg.gestor.domain.model.HorasApontadas;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.repository.HorasApontadasRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HorasService {

    private HorasApontadasRepository horasApontadasRepository;
    private ProjetoRepository projetoRepository;
    private UsuarioRepository usuarioRepository;

    public List<HorasApontadas> listarTodos() {
        return horasApontadasRepository.findAll();
    }

    public ArrayList<HorasApontadasTotalDTO> apontamentoTotal(Long projetoId) {
        Projeto projeto = projetoRepository.findByIdProjeto(projetoId);
        List<HorasApontadas> horasDoProjeto = horasApontadasRepository.findAllInAProject(projeto);
        return convert(horasDoProjeto);
    }

    public ArrayList<HorasApontadasTotalDTO> convert(List<HorasApontadas> apontadas) {
        ArrayList<HorasApontadasTotalDTO> horasTotais = new ArrayList<HorasApontadasTotalDTO>();
        long ultimaId = 0;
        for (int i = 0; i < apontadas.size(); ++i) {
            if (!(ultimaId == apontadas.get(i).getUsuario().getId())) {
                List<HorasApontadas> porUsuario = horasApontadasRepository.findAllProjectAndUsuario(
                        projetoRepository.findByIdProjeto(apontadas.get(i).getProjeto().getId()),
                        usuarioRepository.findByIdUsuario(apontadas.get(i).getUsuario().getId()));
                HorasApontadasTotalDTO horasApontadasTotalDTO = new HorasApontadasTotalDTO();
                horasApontadasTotalDTO.setConsultor_id(porUsuario.get(0).getUsuario().getId());
                horasApontadasTotalDTO.setStatus(porUsuario.get(0).getStatus());
                horasApontadasTotalDTO.setNome(porUsuario.get(0).getUsuario().getNome());
                horasApontadasTotalDTO.setHorasTotais(horasApontadasRepository.buscarHoraTotalUser(
                        projetoRepository.findByIdProjeto(apontadas.get(i).getProjeto().getId()),
                        usuarioRepository.findByIdUsuario(apontadas.get(i).getUsuario().getId())));
                ultimaId = horasApontadasTotalDTO.getConsultor_id();
                horasTotais.add(horasApontadasTotalDTO);
            }
        }
        return horasTotais;

    }

}
