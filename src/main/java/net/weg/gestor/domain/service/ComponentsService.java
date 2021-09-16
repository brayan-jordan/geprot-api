package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.model.HorasApontadas;
import net.weg.gestor.domain.repository.HorasApontadasRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ComponentsService {

    private HorasApontadasRepository horasApontadasRepository;
    private ProjetoRepository projetoRepository;
    private UsuarioRepository usuarioRepository;

    public List<HorasApontadas> buscarHorasApontadas(Long projetoId, Long usuarioId) {
        return (horasApontadasRepository.findStatus(
                projetoRepository.findByIdProjeto(projetoId),
                usuarioRepository.findByIdUsuario(usuarioId),
                "PENDENTE"
        ));
    }

}
