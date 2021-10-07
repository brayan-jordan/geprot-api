package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.repository.HoraApontadaRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ComponentsService {

    private HoraApontadaRepository horaApontadaRepository;
    private ProjetoRepository projetoRepository;
    private UsuarioRepository usuarioRepository;

//    public List<HoraApontada> buscarHorasPorStatus(Long projetoId, Long usuarioId, String status) {
//        return (horaApontadaRepository.findStatus(
//                projetoRepository.findByIdProjeto(projetoId),
//                usuarioRepository.findByIdUsuario(usuarioId),
//                status
//        ));
//    }

}
